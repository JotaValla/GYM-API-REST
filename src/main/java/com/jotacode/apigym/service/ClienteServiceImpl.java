package com.jotacode.apigym.service;

import com.jotacode.apigym.error.ClienteException;
import com.jotacode.apigym.error.MembresiaException;
import com.jotacode.apigym.error.dto.ClienteMembresiaDTO;
import com.jotacode.apigym.model.data.ClienteRepository;
import com.jotacode.apigym.model.data.MembresiaRepository;
import com.jotacode.apigym.model.entity.Cliente;
import com.jotacode.apigym.model.entity.Membresia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    MembresiaRepository membresiaRepository;

    //Obtener todos los clientes
    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) throws ClienteException, MembresiaException {

        //Si no se inserto el numero de cedula
        if (cliente.getCedula() == null || "".equalsIgnoreCase(cliente.getCedula())) {
            throw new ClienteException("El numero de cedula es requerido");
        }
        //Validar los campos de la direccion no sean nulos o vacios
        if (cliente.getDireccion().getCiudad() == null || "".equalsIgnoreCase(cliente.getDireccion().getCiudad())) {
            throw new ClienteException("La ciudad es requerida");
        }
        if (cliente.getDireccion().getCallePrincipal() == null || "".equalsIgnoreCase(cliente.getDireccion().getCallePrincipal())) {
            throw new ClienteException("La calle principal es requerida");
        }
        if (cliente.getDireccion().getCalleSecundaria() == null || "".equalsIgnoreCase(cliente.getDireccion().getCalleSecundaria())) {
            throw new ClienteException("La calle secundaria es requerida");
        }

        //Validar que deba al cliente asignarle una membresia
        if (cliente.getMembresia() == null) {
            throw new MembresiaException("El cliente debe tener una membresia");
        }

        // Verificar si la membresía existe
        Optional<Membresia> membresiaOptional = membresiaRepository.findById(cliente.getMembresia().getIdMembresia());
        if (membresiaOptional.isEmpty()) {
            throw new MembresiaException("La membresía no existe");
        }

        Cliente clienteConMembresia = membresiaOptional.get().getCliente();
        if (clienteConMembresia != null) {
            throw new MembresiaException("La membresía ya está asignada a otro cliente");
        }

        membresiaOptional.get().setCliente(cliente);
        membresiaRepository.save(membresiaOptional.get());

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findClienteById(String id) throws ClienteException {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente updateCliente(Cliente cliente, String id) throws ClienteException {
        Cliente clienteToUpdate = clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("Cliente con id: " + id + " no encontrado")
        );

        // Verificar que solo se actualicen los campos permitidos
        if (cliente.getCedula() != null && !cliente.getCedula().equals(clienteToUpdate.getCedula())) {
            throw new ClienteException("No se permite actualizar la cédula");
        }
        if (cliente.getNombre() != null && !cliente.getNombre().equals(clienteToUpdate.getNombre())) {
            clienteToUpdate.setNombre(cliente.getNombre());
        }
        if (cliente.getApellido() != null && !cliente.getApellido().equals(clienteToUpdate.getApellido())) {
            clienteToUpdate.setApellido(cliente.getApellido());
        }
        if (cliente.getFechaNacimiento() != null && !cliente.getFechaNacimiento().equals(clienteToUpdate.getFechaNacimiento())) {
            clienteToUpdate.setFechaNacimiento(cliente.getFechaNacimiento());
        }

        //Actualizar los datos permitidos
        if (Objects.nonNull(cliente.getCorreo())) clienteToUpdate.setCorreo(cliente.getCorreo());
        if (Objects.nonNull(cliente.getTelefono())) clienteToUpdate.setTelefono(cliente.getTelefono());
        if (Objects.nonNull(cliente.getDireccion())) clienteToUpdate.setDireccion(cliente.getDireccion());

        //Actualizar el estado del cliente si se elimina su membresia igualmente se elimina la membresia
        if(cliente.getActivo() != clienteToUpdate.getActivo()){
            clienteToUpdate.setActivo(cliente.getActivo());
            if (cliente.getMembresia() != null) {
                Membresia membresia = cliente.getMembresia();
                membresia.setEstadoMembresia(cliente.getActivo());
                membresiaRepository.save(membresia);
            }
        }

        return clienteRepository.save(clienteToUpdate);
    }

    @Override
    public void deleteCliente(String id) throws ClienteException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("Cliente con id: " + id + " no encontrado")
        );

        //Descativar el cliente
        cliente.setActivo(false);
        clienteRepository.save(cliente);

        //Eliminar la membresia asociada
        Membresia membresia = cliente.getMembresia();
        if (membresia == null) {
            throw new ClienteException("El cliente no tiene membresia");
        }
        membresia.setEstadoMembresia(false);
        membresiaRepository.save(membresia);

    }

    @Override
    public List<ClienteMembresiaDTO> getClienteMembresiaDetails() {
        return clienteRepository.findClienteMembresiaDetails();
    }
}

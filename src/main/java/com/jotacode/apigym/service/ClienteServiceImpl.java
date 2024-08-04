package com.jotacode.apigym.service;

import com.jotacode.apigym.model.data.ClienteRepository;
import com.jotacode.apigym.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findClienteById(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente updateCliente(Cliente cliente, String id) {
        Cliente clienteToUpdate = clienteRepository.findById(id).orElse(null);
        if (Objects.nonNull(cliente.getNombre()) && !"".equalsIgnoreCase(cliente.getNombre())) {
            clienteToUpdate.setNombre(cliente.getNombre());
        }
        if (Objects.nonNull(cliente.getApellido()) && !"".equalsIgnoreCase(cliente.getApellido())) {
            clienteToUpdate.setApellido(cliente.getApellido());
        }
        if (Objects.nonNull(cliente.getCorreo()) && !"".equalsIgnoreCase(cliente.getCorreo())) {
            clienteToUpdate.setCorreo(cliente.getCorreo());
        }
        if (Objects.nonNull(cliente.getTelefono()) && !"".equalsIgnoreCase(cliente.getTelefono())) {
            clienteToUpdate.setTelefono(cliente.getTelefono());
        }
        //Direccion ciudad
        if (Objects.nonNull(cliente.getDireccion().getCiudad()) && !"".equalsIgnoreCase(cliente.getDireccion().getCiudad())) {
            clienteToUpdate.getDireccion().setCiudad(cliente.getDireccion().getCiudad());
        }
        //Calle principal
        if (Objects.nonNull(cliente.getDireccion().getCallePrincipal()) && !"".equalsIgnoreCase(cliente.getDireccion().getCallePrincipal())) {
            clienteToUpdate.getDireccion().setCallePrincipal(cliente.getDireccion().getCallePrincipal());
        }
        //Segunda calle
        if (Objects.nonNull(cliente.getDireccion().getCalleSecundaria()) && !"".equalsIgnoreCase(cliente.getDireccion().getCalleSecundaria())) {
            clienteToUpdate.getDireccion().setCalleSecundaria(cliente.getDireccion().getCalleSecundaria());
        }
        if (Objects.nonNull(cliente.getFechaNacimiento()) && !"".equalsIgnoreCase(String.valueOf(cliente.getFechaNacimiento()))) {
            clienteToUpdate.setFechaNacimiento(cliente.getFechaNacimiento());
        }
        return clienteRepository.save(clienteToUpdate);
    }

    @Override
    public void deleteCliente(String id) {
        clienteRepository.deleteById(id);
    }
}

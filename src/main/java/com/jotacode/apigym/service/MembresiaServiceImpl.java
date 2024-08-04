package com.jotacode.apigym.service;

import com.jotacode.apigym.error.MembresiaException;
import com.jotacode.apigym.model.data.ClienteRepository;
import com.jotacode.apigym.model.data.MembresiaRepository;
import com.jotacode.apigym.model.entity.Cliente;
import com.jotacode.apigym.model.entity.Membresia;
import com.jotacode.apigym.model.entity.TipoMembresia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MembresiaServiceImpl implements MembresiaService {

    @Autowired
    MembresiaRepository membresiaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Membresia> findAllMembresias() {
        return membresiaRepository.findAll();
    }

    @Override
    public Membresia saveMembresia(Membresia membresia) throws MembresiaException {
        if (membresia.getTipoMembresia() == null) {
            throw new MembresiaException("El tipo de membresia es requerido");
        }
        if (membresia.getFechaCreacion() == null) {
            throw new MembresiaException("La fecha de creación es requerida");
        }
        if (membresia.getFechaExpiracion() == null) {
            throw new MembresiaException("La fecha de expiración es requerida");
        }
        if (membresia.getEstadoMembresia() == null) {
            throw new MembresiaException("El estado de membresia es requerido");
        }
        if (membresia.getPrecio() == 0 || membresia.getPrecio() <= 0) {
            throw new MembresiaException("El precio de membresia es requerido y no puede ser 0 o negativo");
        }
        return membresiaRepository.save(membresia);
    }

    @Override
    public Membresia findMembresiaById(Long id) throws MembresiaException {
        return membresiaRepository.findById(id).orElseThrow(() -> new MembresiaException("Membresia no encontrada"));
    }

    @Override
    public Membresia updateTypeOfMembresia(Membresia membresia, Long id) throws MembresiaException {
        Membresia membresiaToUpdate = membresiaRepository.findById(id).orElseThrow(() -> new MembresiaException("Membresia no encontrada"));

        //validar que los demas campos no se puedan actualizar
        if (membresia.getFechaCreacion() != null && !membresia.getFechaCreacion().equals(membresiaToUpdate.getFechaCreacion())) {
            throw new MembresiaException("La fecha de creación no se puede actualizar");
        }
        if (membresia.getFechaExpiracion() != null && !membresia.getFechaExpiracion().equals(membresiaToUpdate.getFechaExpiracion())) {
            throw new MembresiaException("La fecha de expiración no se puede actualizar");
        }
        if (membresia.getEstadoMembresia() != null && !membresia.getEstadoMembresia().equals(membresiaToUpdate.getEstadoMembresia())) {
            throw new MembresiaException("El estado de membresia no se puede actualizar");
        }

        //Tipo membresia
        if (membresia.getTipoMembresia() != null && !"".equalsIgnoreCase(String.valueOf(membresia.getTipoMembresia()))) {
            membresiaToUpdate.setTipoMembresia(membresia.getTipoMembresia());
        }
        //precio membresia
        if (membresia.getPrecio() != 0 && membresia.getPrecio() > 0) {
            membresiaToUpdate.setPrecio(membresia.getPrecio());
        }
        return membresiaRepository.save(membresiaToUpdate);
    }

    @Override
    public Membresia renovarMembresia(Membresia membresia, Long id) throws MembresiaException {
        Membresia membresiaPorRenovar = membresiaRepository.findById(id).orElseThrow(() -> new MembresiaException("Membresia no encontrada"));

        //validar que lo demas campos no se puedan actualizar
        if (membresia.getTipoMembresia() != null && !membresia.getTipoMembresia().equals(membresiaPorRenovar.getTipoMembresia())) {
            throw new MembresiaException("El tipo de membresia no se puede actualizar");
        }


        //Renovar membresia
        if (membresia.getEstadoMembresia() != membresiaPorRenovar.getEstadoMembresia()) {
            membresiaPorRenovar.setEstadoMembresia(membresia.getEstadoMembresia());
            if (membresia.getCliente() != null) {
                Cliente cliente = membresiaPorRenovar.getCliente();
                cliente.setActivo(membresia.getEstadoMembresia());
                clienteRepository.save(cliente);
            }
        }

        membresiaPorRenovar.setFechaCreacion(membresia.getFechaExpiracion());
        membresiaPorRenovar.setFechaExpiracion(membresia.getFechaExpiracion().plusMonths(1));
        return membresiaRepository.save(membresiaPorRenovar);
    }

    @Override
    public void deleteMembresia(Long id) throws MembresiaException{
        Membresia membresia = membresiaRepository.findById(id).orElseThrow(() -> new MembresiaException("Membresia no encontrada"));

        //desactivar la membresia
        membresia.setEstadoMembresia(false);
        membresiaRepository.save(membresia);

        //desactivar el cliente asociado si existe
        Optional<Cliente> cliente = clienteRepository.findByMembresia(membresia);
        if (cliente.isPresent()) {
            cliente.get().setActivo(false);
            clienteRepository.save(cliente.get());
        }
    }


}

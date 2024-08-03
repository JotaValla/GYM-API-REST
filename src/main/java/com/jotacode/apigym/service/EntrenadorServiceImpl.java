package com.jotacode.apigym.service;

import com.jotacode.apigym.model.data.EntrenadorRepository;
import com.jotacode.apigym.model.entity.Entrenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EntrenadorServiceImpl implements EntrenadorService{

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Override
    public List<Entrenador> findAllEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @Override
    public Entrenador saveEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    @Override
    public Entrenador findEntrenadorById(String id) {
        return entrenadorRepository.findById(id).orElse(null);
    }

    @Override
    public Entrenador updateEntrenador(Entrenador entrenador, String id) {
        Entrenador entrenadorToUpdate = entrenadorRepository.findById(id).orElse(null);
        if (Objects.nonNull(entrenador.getNombre()) && !"".equalsIgnoreCase(entrenador.getNombre())) {
            entrenadorToUpdate.setNombre(entrenador.getNombre());
        }
        if (Objects.nonNull(entrenador.getApellido()) && !"".equalsIgnoreCase(entrenador.getApellido())) {
            entrenadorToUpdate.setApellido(entrenador.getApellido());
        }
        //Correo
        if (Objects.nonNull(entrenador.getCorreo()) && !"".equalsIgnoreCase(entrenador.getCorreo())) {
            entrenadorToUpdate.setCorreo(entrenador.getCorreo());
        }
        //Telefono
        if (Objects.nonNull(entrenador.getTelefono()) && !"".equalsIgnoreCase(entrenador.getTelefono())) {
            entrenadorToUpdate.setTelefono(entrenador.getTelefono());
        }
        //Direccion ciudad
        if (Objects.nonNull(entrenador.getDireccion().getCiudad()) && !"".equalsIgnoreCase(entrenador.getDireccion().getCiudad())) {
            entrenadorToUpdate.getDireccion().setCiudad(entrenador.getDireccion().getCiudad());
        }
        //Calle principal
        if (Objects.nonNull(entrenador.getDireccion().getCallePrincipal()) && !"".equalsIgnoreCase(entrenador.getDireccion().getCallePrincipal())) {
            entrenadorToUpdate.getDireccion().setCallePrincipal(entrenador.getDireccion().getCallePrincipal());
        }
        //Calle secundaria
        if (Objects.nonNull(entrenador.getDireccion().getCalleSecundaria()) && !"".equalsIgnoreCase(entrenador.getDireccion().getCalleSecundaria())) {
            entrenadorToUpdate.getDireccion().setCalleSecundaria(entrenador.getDireccion().getCalleSecundaria());
        }
        //Especialidad
        if (Objects.nonNull(entrenador.getEspecialidad()) && !"".equalsIgnoreCase(entrenador.getEspecialidad())) {
            entrenadorToUpdate.setEspecialidad(entrenador.getEspecialidad());
        }
        return entrenadorRepository.save(entrenadorToUpdate);
    }

    @Override
    public void deleteEntrenador(String id) {
        entrenadorRepository.deleteById(id);
    }
}

package com.jotacode.apigym.service;

import com.jotacode.apigym.error.EntrenadorException;
import com.jotacode.apigym.model.data.EntrenadorRepository;
import com.jotacode.apigym.model.data.EntrenamientoRepository;
import com.jotacode.apigym.model.entity.Entrenador;
import com.jotacode.apigym.model.entity.Entrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    EntrenamientoRepository entrenamientoRepository;

    @Override
    public List<Entrenador> findAllEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @Override
    public Entrenador saveEntrenador(Entrenador entrenador) throws EntrenadorException {

        //Si no inserto el numero de cedula
        if (entrenador.getCedula() == null || "".equalsIgnoreCase(entrenador.getCedula())) {
            throw new EntrenadorException("El numero de cedula es requerido");
        }
        //Validar los campos de la direccion no sean nulos o vacios
        if (entrenador.getDireccion().getCiudad() == null || "".equalsIgnoreCase(entrenador.getDireccion().getCiudad())) {
            throw new EntrenadorException("La ciudad es requerida");
        }
        //Especialidad
        if (entrenador.getEspecialidad() == null || "".equalsIgnoreCase(entrenador.getEspecialidad())) {
            throw new EntrenadorException("La especialidad es requerida");
        }
        return entrenadorRepository.save(entrenador);
    }

    @Override
    public Entrenador findEntrenadorById(String id) throws EntrenadorException {
        return entrenadorRepository.findById(id).orElseThrow(
                () -> new EntrenadorException("Entrenador no encontrado")
        );
    }

    @Override
    public Entrenador updateEntrenador(Entrenador entrenador, String id) throws EntrenadorException {
        Entrenador entrenadorToUpdate = entrenadorRepository.findById(id).orElseThrow(
                () -> new EntrenadorException("Entrenador no encontrado")
        );

        // Verificar que solo se actualicen los campos permitidos
        if (entrenador.getCedula() != null && !entrenador.getCedula().equals(entrenadorToUpdate.getCedula())) {
            throw new EntrenadorException("No se puede actualizar la cedula");
        }

        // Actualizar los campos básicos
        if (entrenador.getNombre() != null) {
            entrenadorToUpdate.setNombre(entrenador.getNombre());
        }
        if (entrenador.getApellido() != null) {
            entrenadorToUpdate.setApellido(entrenador.getApellido());
        }
        if (entrenador.getFechaNacimiento() != null) {
            entrenadorToUpdate.setFechaNacimiento(entrenador.getFechaNacimiento());
        }
        if (entrenador.getDireccion() != null) {
            entrenadorToUpdate.setDireccion(entrenador.getDireccion());
        }
        if (entrenador.getEspecialidad() != null) {
            entrenadorToUpdate.setEspecialidad(entrenador.getEspecialidad());
        }
        if (entrenador.getTelefono() != null) {
            entrenadorToUpdate.setTelefono(entrenador.getTelefono());
        }
        if (entrenador.getCorreo() != null) {
            entrenadorToUpdate.setCorreo(entrenador.getCorreo());
        }

        //No permitir editar entrenamientos
        if (entrenador.getEntrenamientos() != null) {
            throw new EntrenadorException("No se pueden editar los entrenamientos, borrelos o agregeleos nuevos");
        }

        // Actualizar el estado activo
        if (entrenador.getActivo() != null && entrenador.getActivo() != entrenadorToUpdate.getActivo()) {
            entrenadorToUpdate.setActivo(entrenador.getActivo());
        }


        return entrenadorRepository.save(entrenadorToUpdate);
    }

    @Override
    public void deleteEntrenador(String id) throws EntrenadorException {
        Entrenador entrenador = entrenadorRepository.findById(id).orElseThrow(
                () -> new EntrenadorException("Entrenador no encontrado")
        );

        //Desactivar el entrenador
        entrenador.setActivo(false);
        entrenadorRepository.save(entrenador);
    }
}

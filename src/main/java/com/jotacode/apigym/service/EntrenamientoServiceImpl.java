package com.jotacode.apigym.service;

import com.jotacode.apigym.error.EntrenadorException;
import com.jotacode.apigym.error.EntrenamientoException;
import com.jotacode.apigym.model.data.EntrenadorRepository;
import com.jotacode.apigym.model.data.EntrenamientoRepository;
import com.jotacode.apigym.model.entity.Entrenador;
import com.jotacode.apigym.model.entity.Entrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService {
    @Autowired
    EntrenamientoRepository entrenamientoRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Override
    public List<Entrenamiento> findAllEntrenamientos() {
        return entrenamientoRepository.findAll();
    }

    @Override
    public Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento) throws EntrenamientoException {

        //validaciones
        if (entrenamiento.getNombre() == null || "".equalsIgnoreCase(entrenamiento.getNombre())) {
            throw new EntrenamientoException("Nombre es requerido");
        }
        if (entrenamiento.getDescripcion() == null || "".equalsIgnoreCase(entrenamiento.getDescripcion())) {
            throw new EntrenamientoException("Descripción es requerida");
        }
        //duracion
        if (entrenamiento.getDuracion() == 0) {
            throw new EntrenamientoException("Duración es requerida");
        }
        //validar que no sea nulo el entrenador
        if (entrenamiento.getEntrenador() == null) {
            throw new EntrenamientoException("Entrenador es requerido");
        }
        //validar que el entrenador exista
        if (entrenadorRepository.findById(entrenamiento.getEntrenador().getCedula()).isEmpty()) {
            throw new EntrenamientoException("Entrenador no existe");
        }


        return entrenamientoRepository.save(entrenamiento);
    }

    @Override
    public Entrenamiento findEntrenamientoById(Long id) throws EntrenamientoException {
        return entrenamientoRepository.findById(id).orElseThrow(
                () -> new EntrenamientoException("Entrenamiento no encontrado")
        );
    }

    @Override
    public Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento, Long id) throws EntrenamientoException, EntrenadorException {
        Entrenamiento entrenamientoToUpdate = entrenamientoRepository.findById(id).orElseThrow(
                () -> new EntrenamientoException("Entrenamiento no encontrado")

        );
        //Validar que no se editen los campos requeridos
        if (entrenamiento.getNombre() != null && !entrenamiento.getNombre().equals(entrenamientoToUpdate.getNombre())) {
            throw new EntrenamientoException("No se puede editar el nombre");
        }
        if (entrenamiento.getDescripcion() != null && !entrenamiento.getDescripcion().equals(entrenamientoToUpdate.getDescripcion())) {
            throw new EntrenamientoException("No se puede editar la descripción");
        }
        if (entrenamiento.getDuracion() != 0 && entrenamiento.getDuracion() != entrenamientoToUpdate.getDuracion()) {
            throw new EntrenamientoException("No se puede editar la duración");
        }

        if (entrenamiento.getEntrenador() != null && !entrenamiento.getEntrenador().getCedula().equals(entrenamientoToUpdate.getEntrenador().getCedula())) {
            // Validar que el nuevo entrenador exista
            Entrenador nuevoEntrenador = entrenadorRepository.findById(entrenamiento.getEntrenador().getCedula()).orElseThrow(
                    () -> new EntrenadorException("Entrenador no encontrado")
            );

            // Remover el entrenamiento del entrenador anterior
            Entrenador entrenadorAnterior = entrenamientoToUpdate.getEntrenador();
            entrenadorAnterior.getEntrenamientos().remove(entrenamientoToUpdate);
            entrenadorRepository.save(entrenadorAnterior);

            // Asignar el nuevo entrenador al entrenamiento
            entrenamientoToUpdate.setEntrenador(nuevoEntrenador);
            nuevoEntrenador.getEntrenamientos().add(entrenamientoToUpdate);
            entrenadorRepository.save(nuevoEntrenador);
        }


        return entrenamientoRepository.save(entrenamientoToUpdate);

    }

    @Override
    public void deleteEntrenamiento(Long id) throws EntrenamientoException {
        // Buscar el entrenamiento a eliminar
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id).orElseThrow(
                () -> new EntrenamientoException("Entrenamiento no encontrado")
        );

        // Obtener el entrenador asociado
        Entrenador entrenador = entrenamiento.getEntrenador();

        // Si hay un entrenador asociado, actualizar su lista de entrenamientos
        if (entrenador != null) {
            entrenador.getEntrenamientos().remove(entrenamiento);
            entrenadorRepository.save(entrenador);
        }

        // Eliminar el entrenamiento
        entrenamientoRepository.deleteById(id);
    }
}

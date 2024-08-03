package com.jotacode.apigym.service;

import com.jotacode.apigym.model.data.EntrenamientoRepository;
import com.jotacode.apigym.model.entity.Entrenamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenamientoServiceImpl implements EntrenamientoService{
    @Autowired
    EntrenamientoRepository entrenamientoRepository;

    @Override
    public List<Entrenamiento> findAllEntrenamientos() {
        return entrenamientoRepository.findAll();
    }

    @Override
    public Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento) {
        return entrenamientoRepository.save(entrenamiento);
    }

    @Override
    public Entrenamiento findEntrenamientoById(Long id) {
        return entrenamientoRepository.findById(id).orElse(null);
    }

    @Override
    public Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento, Long id) {
        Entrenamiento entrenamientoToUpdate = entrenamientoRepository.findById(id).orElse(null);
        if(entrenamiento.getNombre() != null && !"".equalsIgnoreCase(entrenamiento.getNombre())){
            entrenamientoToUpdate.setNombre(entrenamiento.getNombre());
        }
        if(entrenamiento.getDescripcion() != null && !"".equalsIgnoreCase(entrenamiento.getDescripcion())){
            entrenamientoToUpdate.setDescripcion(entrenamiento.getDescripcion());
        }

        return entrenamientoRepository.save(entrenamientoToUpdate);

    }

    @Override
    public void deleteEntrenamiento(Long id) {
        entrenamientoRepository.deleteById(id);
    }
}

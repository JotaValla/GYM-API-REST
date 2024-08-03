package com.jotacode.apigym.service;

import com.jotacode.apigym.model.entity.Entrenamiento;

import java.util.List;

public interface EntrenamientoService {

    List<Entrenamiento> findAllEntrenamientos();
    Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento);
    Entrenamiento findEntrenamientoById(Long id);
    Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento, Long id);
    void deleteEntrenamiento(Long id);


}

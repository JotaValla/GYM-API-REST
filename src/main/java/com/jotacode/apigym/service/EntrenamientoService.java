package com.jotacode.apigym.service;

import com.jotacode.apigym.error.EntrenadorException;
import com.jotacode.apigym.error.EntrenamientoException;
import com.jotacode.apigym.model.entity.Entrenamiento;

import java.util.List;

public interface EntrenamientoService {

    List<Entrenamiento> findAllEntrenamientos();
    Entrenamiento saveEntrenamiento(Entrenamiento entrenamiento) throws EntrenamientoException;
    Entrenamiento findEntrenamientoById(Long id)throws EntrenamientoException;
    Entrenamiento updateEntrenamiento(Entrenamiento entrenamiento, Long id) throws EntrenamientoException, EntrenadorException;
    void deleteEntrenamiento(Long id)throws EntrenamientoException;


}

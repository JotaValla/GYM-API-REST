package com.jotacode.apigym.service;

import com.jotacode.apigym.model.entity.Entrenador;

import java.util.List;

public interface EntrenadorService {

    List<Entrenador> findAllEntrenadores();
    Entrenador saveEntrenador(Entrenador entrenador);
    Entrenador findEntrenadorById(String id);
    Entrenador updateEntrenador(Entrenador entrenador, String id);
    void deleteEntrenador(String id);


}

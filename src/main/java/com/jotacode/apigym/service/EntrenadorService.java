package com.jotacode.apigym.service;

import com.jotacode.apigym.error.EntrenadorException;
import com.jotacode.apigym.model.entity.Entrenador;

import java.util.List;

public interface EntrenadorService {

    List<Entrenador> findAllEntrenadores();
    Entrenador saveEntrenador(Entrenador entrenador) throws EntrenadorException;
    Entrenador findEntrenadorById(String id) throws EntrenadorException;
    Entrenador updateEntrenador(Entrenador entrenador, String id) throws EntrenadorException;
    void deleteEntrenador(String id) throws EntrenadorException;


}

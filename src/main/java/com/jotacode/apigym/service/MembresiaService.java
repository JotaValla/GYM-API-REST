package com.jotacode.apigym.service;

import com.jotacode.apigym.model.entity.Membresia;

import java.util.List;

public interface MembresiaService {

    List<Membresia> findAllMembresias();
    Membresia saveMembresia(Membresia membresia);
    Membresia findMembresiaById(Long id);
    Membresia updateMembresia(Membresia membresia, Long id);
    void deleteMembresia(Long id);


}

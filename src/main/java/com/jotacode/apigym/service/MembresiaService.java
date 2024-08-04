package com.jotacode.apigym.service;

import com.jotacode.apigym.error.MembresiaException;
import com.jotacode.apigym.model.entity.Membresia;

import java.util.List;

public interface MembresiaService {

    List<Membresia> findAllMembresias();

    Membresia saveMembresia(Membresia membresia) throws MembresiaException;

    Membresia findMembresiaById(Long id) throws MembresiaException;

    Membresia updateTypeOfMembresia(Membresia membresia, Long id) throws MembresiaException;

    void deleteMembresia(Long id) throws MembresiaException;

    Membresia renovarMembresia(Membresia membresia, Long id) throws MembresiaException;

}

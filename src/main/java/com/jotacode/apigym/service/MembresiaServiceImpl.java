package com.jotacode.apigym.service;

import com.jotacode.apigym.model.data.MembresiaRepository;
import com.jotacode.apigym.model.entity.Membresia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembresiaServiceImpl implements MembresiaService{

    @Autowired
    MembresiaRepository membresiaRepository;

    @Override
    public List<Membresia> findAllMembresias() {
        return membresiaRepository.findAll();
    }

    @Override
    public Membresia saveMembresia(Membresia membresia) {
        return membresiaRepository.save(membresia);
    }

    @Override
    public Membresia findMembresiaById(Long id) {
        return membresiaRepository.findById(id).orElse(null);
    }

    @Override
    public Membresia updateMembresia(Membresia membresia, Long id) {
        Membresia membresiaToUpdate = membresiaRepository.findById(id).orElse(null);
        //Tipo membresia
        if(membresia.getTipoMembresia() != null && !"".equalsIgnoreCase(String.valueOf(membresia.getTipoMembresia()))){
            membresiaToUpdate.setTipoMembresia(membresia.getTipoMembresia());
        }
        return membresiaRepository.save(membresiaToUpdate);
    }

    @Override
    public void deleteMembresia(Long id) {
        membresiaRepository.deleteById(id);
    }
}

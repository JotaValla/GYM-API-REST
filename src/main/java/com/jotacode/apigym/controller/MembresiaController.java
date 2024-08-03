package com.jotacode.apigym.controller;

import com.jotacode.apigym.model.entity.Membresia;
import com.jotacode.apigym.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembresiaController {

    @Autowired
    MembresiaService membresiaService;

    //Obtener todas las membresias
    @GetMapping("/membresias")
    public List<Membresia> getMembresias(){
        return membresiaService.findAllMembresias();
    }

    //Postear una membresia
    @PostMapping("/crearMembresia")
    public Membresia saveMembresia(@RequestBody Membresia membresia){
        return membresiaService.saveMembresia(membresia);
    }

    //Obtener una membresia por id
    @GetMapping("/membresia/{id}")
    public Membresia getMembresiaById(@PathVariable Long id){
        return membresiaService.findMembresiaById(id);
    }

    //Actualizar una membresia por id
    @PostMapping("/actualizarMembresia/{id}")
    public Membresia updateMembresia(@RequestBody Membresia membresia, @PathVariable Long id){
        return membresiaService.updateMembresia(membresia, id);
    }

    //Eliminar una membresia por id
    @DeleteMapping("/eliminarMembresia/{id}")
    public void deleteMembresiaById(@PathVariable Long id){
        membresiaService.deleteMembresia(id);
    }


}

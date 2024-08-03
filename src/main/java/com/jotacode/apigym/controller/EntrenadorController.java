package com.jotacode.apigym.controller;

import com.jotacode.apigym.model.entity.Entrenador;
import com.jotacode.apigym.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntrenadorController {

    @Autowired
    EntrenadorService entrenadorService;

    //Obtener todos los entrenadores
    @GetMapping("/entrenadores")
    public List<Entrenador> getEntrenadores(){
        return entrenadorService.findAllEntrenadores();
    }

    //Postear un entrenador
    @PostMapping("/crearEntrenador")
    public Entrenador saveEntrenador(@RequestBody Entrenador entrenador){
        return entrenadorService.saveEntrenador(entrenador);
    }

    //Obtener un entrenador por id
    @GetMapping("/entrenador/{id}")
    public Entrenador getEntrenadorById(@PathVariable String id){
        return entrenadorService.findEntrenadorById(id);
    }

    //Actualizar un entrenador por id
    @PostMapping("/actualizarEntrenador/{id}")
    public Entrenador updateEntrenador(@RequestBody Entrenador entrenador, @PathVariable String id){
        return entrenadorService.updateEntrenador(entrenador, id);
    }

    //Eliminar un entrenador por id
    @DeleteMapping("/eliminarEntrenador/{id}")
    public void deleteEntrenadorById(@PathVariable String id){
        entrenadorService.deleteEntrenador(id);
    }

}

package com.jotacode.apigym.controller;

import com.jotacode.apigym.error.EntrenadorException;
import com.jotacode.apigym.model.entity.Entrenador;
import com.jotacode.apigym.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntrenadorController {

    @Autowired
    EntrenadorService entrenadorService;

    //Obtener todos los entrenadores
    @GetMapping("/entrenadores")
    public ResponseEntity<List<Entrenador>> getEntrenadores(){
        return ResponseEntity.ok(entrenadorService.findAllEntrenadores());
    }

    //Postear un entrenador
    @PostMapping("/crearEntrenador")
    public ResponseEntity<Entrenador> saveEntrenador(@Valid @RequestBody Entrenador entrenador) throws EntrenadorException {
        return ResponseEntity.ok(entrenadorService.saveEntrenador(entrenador));
    }

    //Obtener un entrenador por id
    @GetMapping("/entrenador/{id}")
    public ResponseEntity<Entrenador> getEntrenadorById(@PathVariable String id) throws EntrenadorException {
        return ResponseEntity.ok(entrenadorService.findEntrenadorById(id));
    }

    //Actualizar un entrenador por id
    @PutMapping("/actualizarEntrenador/{id}")
    public ResponseEntity<Entrenador> updateEntrenador(@RequestBody Entrenador entrenador, @PathVariable String id) throws EntrenadorException {
        return ResponseEntity.ok(entrenadorService.updateEntrenador(entrenador, id));
    }

    //Eliminar un entrenador por id
    @DeleteMapping("/eliminarEntrenador/{id}")
    public ResponseEntity<Void> deleteEntrenadorById(@PathVariable String id) throws EntrenadorException {
        entrenadorService.deleteEntrenador(id);
        return ResponseEntity.ok().build();
    }

}

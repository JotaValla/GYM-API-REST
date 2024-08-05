package com.jotacode.apigym.controller;

import com.jotacode.apigym.error.EntrenadorException;
import com.jotacode.apigym.error.EntrenamientoException;
import com.jotacode.apigym.model.entity.Entrenamiento;
import com.jotacode.apigym.service.EntrenamientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntrenamientoController {

    @Autowired
    EntrenamientoService entrenamientoService;

    //Obtener todos los entrenamientos
    @GetMapping("/entrenamientos")
    public ResponseEntity<List<Entrenamiento>> getEntrenamientos(){
        return ResponseEntity.ok(entrenamientoService.findAllEntrenamientos());
    }

    //Postear un entrenamiento
    @PostMapping("/crearEntrenamiento")
    public ResponseEntity<Entrenamiento> saveEntrenamiento(@Valid @RequestBody Entrenamiento entrenamiento) throws EntrenamientoException {
        return ResponseEntity.ok(entrenamientoService.saveEntrenamiento(entrenamiento));
    }

    //Obtener un entrenamiento por id
    @GetMapping("/entrenamiento/{id}")
    public ResponseEntity<Entrenamiento> getEntrenamientoById(@PathVariable Long id) throws EntrenamientoException {
        return ResponseEntity.ok(entrenamientoService.findEntrenamientoById(id));
    }

    //Actualizar un entrenamiento por id
    @PutMapping("/actualizarEntrenamiento/{id}")
    public ResponseEntity<Entrenamiento> updateEntrenamiento(@RequestBody Entrenamiento entrenamiento, @PathVariable Long id) throws EntrenamientoException, EntrenadorException {
        return ResponseEntity.ok(entrenamientoService.updateEntrenamiento(entrenamiento, id));
    }

    //Eliminar un entrenamiento por id
    @DeleteMapping("/eliminarEntrenamiento/{id}")
    public ResponseEntity<Void> deleteEntrenamientoById(@PathVariable Long id) throws EntrenamientoException {
        entrenamientoService.deleteEntrenamiento(id);
        return ResponseEntity.noContent().build();
    }
}

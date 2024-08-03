package com.jotacode.apigym.controller;

import com.jotacode.apigym.model.entity.Entrenamiento;
import com.jotacode.apigym.service.EntrenamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntrenamientoController {

    @Autowired
    EntrenamientoService entrenamientoService;

    //Obtener todos los entrenamientos
    @GetMapping("/entrenamientos")
    public List<Entrenamiento> getEntrenamientos(){
        return entrenamientoService.findAllEntrenamientos();
    }

    //Postear un entrenamiento
    @PostMapping("/crearEntrenamiento")
    public Entrenamiento saveEntrenamiento(@RequestBody Entrenamiento entrenamiento){
        return entrenamientoService.saveEntrenamiento(entrenamiento);
    }

    //Obtener un entrenamiento por id
    @GetMapping("/entrenamiento/{id}")
    public Entrenamiento getEntrenamientoById(@PathVariable Long id){
        return entrenamientoService.findEntrenamientoById(id);
    }

    //Actualizar un entrenamiento por id
    @PostMapping("/actualizarEntrenamiento/{id}")
    public Entrenamiento updateEntrenamiento(@RequestBody Entrenamiento entrenamiento, @PathVariable Long id){
        return entrenamientoService.updateEntrenamiento(entrenamiento, id);
    }

    //Eliminar un entrenamiento por id
    @DeleteMapping("/eliminarEntrenamiento/{id}")
    public void deleteEntrenamientoById(@PathVariable Long id){
        entrenamientoService.deleteEntrenamiento(id);
    }
}

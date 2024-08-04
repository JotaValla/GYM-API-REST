package com.jotacode.apigym.controller;

import com.jotacode.apigym.error.MembresiaException;
import com.jotacode.apigym.model.entity.Membresia;
import com.jotacode.apigym.service.MembresiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembresiaController {

    @Autowired
    MembresiaService membresiaService;

    //Obtener todas las membresias
    @GetMapping("/membresias")
    public ResponseEntity<List<Membresia>> getMembresias(){
        return ResponseEntity.ok(membresiaService.findAllMembresias());
    }

    //Postear una membresia
    @PostMapping("/crearMembresia")
    public ResponseEntity<Membresia> saveMembresia(@Valid @RequestBody Membresia membresia) throws MembresiaException {
        if (membresia.getTipoMembresia() == null) {
            throw new MembresiaException("El tipo de membresia es requerido");
        }
        return ResponseEntity.ok(membresiaService.saveMembresia(membresia));
    }

    //Obtener una membresia por id
    @GetMapping("/membresia/{id}")
    public ResponseEntity<Membresia> getMembresiaById(@PathVariable Long id) throws MembresiaException {
        return ResponseEntity.ok(membresiaService.findMembresiaById(id));
    }

    //Actualizar una membresia por id
    @PutMapping("/actualizarMembresia/{id}")
    public ResponseEntity<Membresia> updateMembresia(@RequestBody Membresia membresia, @PathVariable Long id) throws MembresiaException {
        return ResponseEntity.ok(membresiaService.updateTypeOfMembresia(membresia, id));
    }

    @PutMapping("/renovarMembresia/{id}")
    public ResponseEntity<Membresia> renovarMembresia(@RequestBody Membresia membresia, @PathVariable Long id) throws MembresiaException {
        return ResponseEntity.ok(membresiaService.renovarMembresia(membresia, id));
    }

    //Eliminar una membresia por id
    @DeleteMapping("/eliminarMembresia/{id}")
    public ResponseEntity<Void> deleteMembresiaById(@PathVariable Long id) throws MembresiaException {
        membresiaService.deleteMembresia(id);
        return ResponseEntity.noContent().build();
    }


}

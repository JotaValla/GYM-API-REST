package com.jotacode.apigym.controller;

import com.jotacode.apigym.error.EmpleadoException;
import com.jotacode.apigym.model.entity.Empleado;
import com.jotacode.apigym.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    //Obtener todos los empleados
    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> getEmpleados(){
        return ResponseEntity.ok(empleadoService.findAllEmpleados());
    }

    //Postear un empleado
    @PostMapping("/crearEmpleado")
    public ResponseEntity<Empleado> saveEmpleado(@Valid @RequestBody Empleado empleado) throws EmpleadoException {
        return ResponseEntity.ok(empleadoService.saveEmpleado(empleado));
    }

    //Obtener un empleado por id
    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable String id) throws EmpleadoException {
        return ResponseEntity.ok(empleadoService.findEmpleadoById(id));
    }

    //Actualizar un empleado por id
    @PostMapping("/actualizarEmpleado/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado, @PathVariable String id) throws EmpleadoException {
        return ResponseEntity.ok(empleadoService.updateEmpleado(empleado, id));
    }

    //Eliminar un empleado por id
    @DeleteMapping("/eliminarEmpleado/{id}")
    public ResponseEntity<Void> deleteEmpleadoById(@PathVariable String id) throws EmpleadoException {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }


}

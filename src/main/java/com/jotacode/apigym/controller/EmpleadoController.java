package com.jotacode.apigym.controller;

import com.jotacode.apigym.model.entity.Empleado;
import com.jotacode.apigym.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    //Obtener todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> getEmpleados(){
        return empleadoService.findAllEmpleados();
    }

    //Postear un empleado
    @PostMapping("/crearEmpleado")
    public Empleado saveEmpleado(@RequestBody Empleado empleado){
        return empleadoService.saveEmpleado(empleado);
    }

    //Obtener un empleado por id
    @GetMapping("/empleado/{id}")
    public Empleado getEmpleadoById(@PathVariable String id){
        return empleadoService.findEmpleadoById(id);
    }

    //Actualizar un empleado por id
    @PostMapping("/actualizarEmpleado/{id}")
    public Empleado updateEmpleado(@RequestBody Empleado empleado, @PathVariable String id){
        return empleadoService.updateEmpleado(empleado, id);
    }

    //Eliminar un empleado por id
    @DeleteMapping("/eliminarEmpleado/{id}")
    public void deleteEmpleadoById(@PathVariable String id){
        empleadoService.deleteEmpleado(id);
    }


}

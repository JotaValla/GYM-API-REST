package com.jotacode.apigym.controller;

import com.jotacode.apigym.model.entity.Cuenta;
import com.jotacode.apigym.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    //Obtener todas las cuentas
    @GetMapping("/cuentas")
    public List<Cuenta> getCuentas() {
        return cuentaService.findAllCuentas();
    }

    //Postear una cuenta
    @PostMapping("/crearCuenta")
    public Cuenta saveCuenta(@Valid @RequestBody Cuenta cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }

    //Obtener una cuenta por id
    @GetMapping("/cuenta/{id}")
    public Cuenta getCuentaById(@PathVariable Long id) {
        return cuentaService.findCuentaById(id);
    }

    //Actualizar una cuenta por id
    @PostMapping("/actualizarCuenta/{id}")
    public Cuenta updateCuenta(@RequestBody Cuenta cuenta, @PathVariable Long id) {
        return cuentaService.updateCuenta(cuenta, id);
    }

    //Eliminar una cuenta por id
    @DeleteMapping("/eliminarCuenta/{id}")
    public void deleteCuentaById(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
    }


}

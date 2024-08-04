package com.jotacode.apigym.controller;

import com.jotacode.apigym.error.CuentaException;
import com.jotacode.apigym.error.EmpleadoException;
import com.jotacode.apigym.model.data.CuentaRepository;
import com.jotacode.apigym.model.entity.Cuenta;
import com.jotacode.apigym.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;


    //Obtener todas las cuentas
    @GetMapping("/cuentas")
    public ResponseEntity<List<Cuenta>> getCuentas() {
        return ResponseEntity.ok(cuentaService.findAllCuentas());
    }

    //Obtener una cuenta por id
    @GetMapping("/cuenta/{id}")
    public Cuenta getCuentaById(@PathVariable Long id) throws CuentaException {
        return ResponseEntity.ok(cuentaService.findCuentaById(id)).getBody();
    }

    //Obtener lista de cuentas activas
    @GetMapping("/cuentasActivas")
    public ResponseEntity<List<Cuenta>> getCuentasActivas() throws CuentaException {
        return ResponseEntity.ok(cuentaService.findCuentasActivas());
    }


    //Postear una cuenta
    @PostMapping("/crearCuenta")
    public ResponseEntity<Cuenta> saveCuenta(@Valid @RequestBody Cuenta cuenta) throws CuentaException {
        return ResponseEntity.ok(cuentaService.saveCuenta(cuenta));
    }

    //Actualizar una cuenta por id
    @PutMapping("/actualizarCuenta/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@RequestBody Cuenta cuenta, @PathVariable Long id) throws EmpleadoException, CuentaException {
        return ResponseEntity.ok(cuentaService.updateCuenta(cuenta, id));
    }

    //Eliminar una cuenta por id
    @DeleteMapping("/eliminarCuenta/{id}")
    public ResponseEntity<Void> deleteCuentaById(@PathVariable Long id) throws CuentaException {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }


}

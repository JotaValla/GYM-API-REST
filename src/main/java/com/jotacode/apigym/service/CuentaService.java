package com.jotacode.apigym.service;

import com.jotacode.apigym.error.CuentaException;
import com.jotacode.apigym.error.EmpleadoException;
import com.jotacode.apigym.model.entity.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    List<Cuenta> findAllCuentas();
    Cuenta saveCuenta(Cuenta cuenta) throws CuentaException;
    Cuenta findCuentaById(Long id) throws CuentaException;
    Cuenta updateCuenta(Cuenta cuenta, Long id) throws EmpleadoException, CuentaException;
    void deleteCuenta(Long id) throws CuentaException;
    Cuenta findCuentaByUsername(String username) throws CuentaException;
    List<Cuenta> findCuentasActivas() throws CuentaException;

}

package com.jotacode.apigym.service;

import com.jotacode.apigym.model.entity.Cuenta;

import java.util.List;

public interface CuentaService {

    List<Cuenta> findAllCuentas();
    Cuenta saveCuenta(Cuenta cuenta);
    Cuenta findCuentaById(Long id);
    Cuenta updateCuenta(Cuenta cuenta, Long id);
    void deleteCuenta(Long id);


}

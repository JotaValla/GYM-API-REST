package com.jotacode.apigym.service;

import com.jotacode.apigym.model.data.CuentaRepository;
import com.jotacode.apigym.model.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> findAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta findCuentaById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta, Long id) {
        Cuenta cuentaToUpdate = cuentaRepository.findById(id).orElse(null);
        if(Objects.nonNull(cuenta.getUsername()) && !"".equalsIgnoreCase(cuenta.getUsername())){
            cuentaToUpdate.setUsername(cuenta.getUsername());
        }
        if(Objects.nonNull(cuenta.getPassword()) && !"".equalsIgnoreCase(cuenta.getPassword())){
            cuentaToUpdate.setPassword(cuenta.getPassword());
        }
        return cuentaRepository.save(cuentaToUpdate);
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}

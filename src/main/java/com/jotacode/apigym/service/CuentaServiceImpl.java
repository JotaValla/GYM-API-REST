package com.jotacode.apigym.service;

import com.jotacode.apigym.error.CuentaException;
import com.jotacode.apigym.error.EmpleadoException;
import com.jotacode.apigym.model.data.CuentaRepository;
import com.jotacode.apigym.model.data.EmpleadoRepository;
import com.jotacode.apigym.model.entity.Cuenta;
import com.jotacode.apigym.model.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Cuenta> findAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta saveCuenta(Cuenta cuenta) throws CuentaException{
        if (cuentaRepository.findByUsernameEqualsIgnoreCase(cuenta.getUsername()).isPresent()){
            throw new CuentaException("El nombre de usuario ya existe");
        }
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta findCuentaById(Long id) throws CuentaException {
        return cuentaRepository.findById(id).orElseThrow(() -> new CuentaException("La cuenta no existe"));
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta, Long id) throws EmpleadoException, CuentaException {
        Cuenta cuentaToUpdate = cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaException("Cuenta con id: " + id + " no encontrada"));

        if (Objects.nonNull(cuenta.getUsername()) && !"".equalsIgnoreCase(cuenta.getUsername())) {
            cuentaToUpdate.setUsername(cuenta.getUsername());
        }
        if (Objects.nonNull(cuenta.getPassword()) && !"".equalsIgnoreCase(cuenta.getPassword())) {
            cuentaToUpdate.setPassword(cuenta.getPassword());
        }

        // Actualizar el estado de la cuenta y el empleado asociado si es necesario
        if (cuenta.getActivo() != cuentaToUpdate.getActivo()) {
            cuentaToUpdate.setActivo(cuenta.getActivo());
            Empleado empleado = empleadoRepository.findByCuenta(cuentaToUpdate)
                    .orElseThrow(() -> new EmpleadoException("Empleado asociado con la cuenta no encontrado"));
            empleado.setActivo(cuenta.getActivo());
            empleadoRepository.save(empleado);
        }

        return cuentaRepository.save(cuentaToUpdate);
    }

    @Override
    public void deleteCuenta(Long id) throws CuentaException {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaException("Cuenta con id: " + id + " no encontrada"));

        // Desactivar la cuenta
        cuenta.setActivo(false);
        cuentaRepository.save(cuenta);

        // Desactivar el empleado asociado si existe
        Optional<Empleado> empleado = empleadoRepository.findByCuenta(cuenta);
        if (empleado.isPresent()) {
            empleado.get().setActivo(false);
            empleadoRepository.save(empleado.get());
        }
    }

    @Override
    public Cuenta findCuentaByUsername(String username) throws CuentaException {
        return cuentaRepository.findByUsernameEqualsIgnoreCase(username).orElseThrow(() -> new CuentaException("La cuenta no existe"));
    }

    @Override
    public List<Cuenta> findCuentasActivas() throws CuentaException {
        return cuentaRepository.findByActivoTrue().orElseThrow(
                () -> new CuentaException("No hay cuentas activas")
        );
    }
}

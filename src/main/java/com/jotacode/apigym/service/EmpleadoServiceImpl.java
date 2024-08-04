package com.jotacode.apigym.service;

import com.jotacode.apigym.error.EmpleadoException;
import com.jotacode.apigym.error.InvalidUpdateException;
import com.jotacode.apigym.model.data.CuentaRepository;
import com.jotacode.apigym.model.data.EmpleadoRepository;
import com.jotacode.apigym.model.entity.Cuenta;
import com.jotacode.apigym.model.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    //Repository
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    public List<Empleado> findAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) throws EmpleadoException {
        // Validar que la cédula exista
        if (empleado.getCedula() == null || empleado.getCedula().isEmpty()) {
            throw new EmpleadoException("La cédula del empleado es requerida");
        }

        // Validar que la cédula no esté ya registrada
        if (empleadoRepository.findById(empleado.getCedula()).isPresent()) {
            throw new EmpleadoException("El empleado con cédula " + empleado.getCedula() + " ya existe");
        }

        // Validar que la cuenta exista y no esté asignada a otro empleado
        if (empleado.getCuenta() == null) {
            throw new EmpleadoException("La cuenta del empleado es requerida");
        } else {
            Cuenta cuenta = cuentaRepository.findById(empleado.getCuenta().getIdCuenta())
                    .orElseThrow(() -> new EmpleadoException("Cuenta con ID: " + empleado.getCuenta().getIdCuenta() + " no encontrada"));

            // Verificar si la cuenta ya está asignada a otro empleado
            if (empleadoRepository.findByCuenta(cuenta).isPresent()) {
                throw new EmpleadoException("La cuenta ya está asignada a otro empleado.");
            }

            empleado.setCuenta(cuenta);
        }

        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado findEmpleadoById(String id) throws EmpleadoException {
        return empleadoRepository.findById(id).orElseThrow(
                () -> new EmpleadoException("Empleado con id: " + id + " no encontrado")
        );
    }

    @Override
    public Empleado updateEmpleado(Empleado empleado, String id) throws EmpleadoException {
        Empleado empleadoToUpdate = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoException("Empleado con id: " + id + " no encontrado"));

        // Verificar que solo se actualicen los campos permitidos
        if (empleado.getCedula() != null && !empleado.getCedula().equals(empleadoToUpdate.getCedula())) {
            throw new InvalidUpdateException("No se permite actualizar la cédula");
        }
        if (empleado.getNombre() != null && !empleado.getNombre().equals(empleadoToUpdate.getNombre())) {
            throw new InvalidUpdateException("No se permite actualizar el nombre");
        }
        if (empleado.getApellido() != null && !empleado.getApellido().equals(empleadoToUpdate.getApellido())) {
            throw new InvalidUpdateException("No se permite actualizar el apellido");
        }
        if (empleado.getFechaNacimiento() != null && !empleado.getFechaNacimiento().equals(empleadoToUpdate.getFechaNacimiento())) {
            throw new InvalidUpdateException("No se permite actualizar la fecha de nacimiento");
        }
        if (empleado.getRol() != null && !empleado.getRol().equals(empleadoToUpdate.getRol())) {
            throw new InvalidUpdateException("No se permite actualizar el rol");
        }
        if (empleado.getCuenta() != null && !empleado.getCuenta().equals(empleadoToUpdate.getCuenta())) {
            throw new InvalidUpdateException("No se permite actualizar la cuenta");
        }

        // Actualizar los detalles permitidos del empleado
        if (Objects.nonNull(empleado.getCorreo())) empleadoToUpdate.setCorreo(empleado.getCorreo());
        if (Objects.nonNull(empleado.getTelefono())) empleadoToUpdate.setTelefono(empleado.getTelefono());
        if (Objects.nonNull(empleado.getDireccion())) empleadoToUpdate.setDireccion(empleado.getDireccion());

        // Actualizar el estado del empleado y la cuenta asociada si es necesario
        if (empleado.getActivo() != empleadoToUpdate.getActivo()) {
            empleadoToUpdate.setActivo(empleado.getActivo());
            if (empleado.getCuenta() != null) {
                Cuenta cuenta = empleado.getCuenta();
                cuenta.setActivo(empleado.getActivo());
                cuentaRepository.save(cuenta);
            }
        }

        return empleadoRepository.save(empleadoToUpdate);
    }

    @Override
    public void deleteEmpleado(String id) throws EmpleadoException {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoException("Empleado con id: " + id + " no encontrado"));

        // Desactivar el empleado
        empleado.setActivo(false);
        empleadoRepository.save(empleado);

        // Desactivar la cuenta asociada si existe
        Cuenta cuenta = empleado.getCuenta();
        if (cuenta != null) {
            cuenta.setActivo(false);
            cuentaRepository.save(cuenta);
        }
    }
}

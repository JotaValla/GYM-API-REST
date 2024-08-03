package com.jotacode.apigym.service;

import com.jotacode.apigym.model.data.EmpleadoRepository;
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

    @Override
    public List<Empleado> findAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado findEmpleadoById(String id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado updateEmpleado(Empleado empleado, String id) {
        Empleado empleadoToUpdate = empleadoRepository.findById(id).orElse(null);
        if (Objects.nonNull(empleado.getNombre()) && !"".equalsIgnoreCase(empleado.getNombre())) {
            empleadoToUpdate.setNombre(empleado.getNombre());
        }
        if (Objects.nonNull(empleado.getApellido()) && !"".equalsIgnoreCase(empleado.getApellido())) {
            empleadoToUpdate.setApellido(empleado.getApellido());
        }
        //Correo
        if (Objects.nonNull(empleado.getCorreo()) && !"".equalsIgnoreCase(empleado.getCorreo())) {
            empleadoToUpdate.setCorreo(empleado.getCorreo());
        }
        //Telefono
        if (Objects.nonNull(empleado.getTelefono()) && !"".equalsIgnoreCase(empleado.getTelefono())) {
            empleadoToUpdate.setTelefono(empleado.getTelefono());
        }
        //Direccion ciudad
        if (Objects.nonNull(empleado.getDireccion().getCiudad()) && !"".equalsIgnoreCase(empleado.getDireccion().getCiudad())) {
            empleadoToUpdate.getDireccion().setCiudad(empleado.getDireccion().getCiudad());
        }
        //Calle principal
        if (Objects.nonNull(empleado.getDireccion().getCallePrincipal()) && !"".equalsIgnoreCase(empleado.getDireccion().getCallePrincipal())) {
            empleadoToUpdate.getDireccion().setCallePrincipal(empleado.getDireccion().getCallePrincipal());
        }
        //Calle secundaria
        if (Objects.nonNull(empleado.getDireccion().getCalleSecundaria()) && !"".equalsIgnoreCase(empleado.getDireccion().getCalleSecundaria())) {
            empleadoToUpdate.getDireccion().setCalleSecundaria(empleado.getDireccion().getCalleSecundaria());
        }
        //Fecha de nacimiento
        if (Objects.nonNull(empleado.getFechaNacimiento()) && !"".equalsIgnoreCase(String.valueOf(empleado.getFechaNacimiento()))) {
            empleadoToUpdate.setFechaNacimiento(empleado.getFechaNacimiento());
        }
        //Rol
        if (Objects.nonNull(empleado.getRol()) && !"".equalsIgnoreCase(empleado.getRol())) {
            empleadoToUpdate.setRol(empleado.getRol());
        }
        return empleadoRepository.save(empleadoToUpdate);

    }

    @Override
    public void deleteEmpleado(String id) {
        empleadoRepository.deleteById(id);
    }
}

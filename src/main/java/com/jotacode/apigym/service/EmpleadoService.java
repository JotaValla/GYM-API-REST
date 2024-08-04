package com.jotacode.apigym.service;


import com.jotacode.apigym.error.EmpleadoException;
import com.jotacode.apigym.model.entity.Empleado;

import java.util.List;

public interface EmpleadoService {

    List<Empleado> findAllEmpleados();
    Empleado saveEmpleado(Empleado empleado) throws EmpleadoException;
    Empleado findEmpleadoById(String id) throws EmpleadoException;
    Empleado updateEmpleado(Empleado empleado, String id) throws EmpleadoException;
    void deleteEmpleado(String id) throws EmpleadoException;


}

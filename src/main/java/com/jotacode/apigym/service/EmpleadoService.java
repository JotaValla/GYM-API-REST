package com.jotacode.apigym.service;


import com.jotacode.apigym.model.entity.Empleado;

import java.util.List;

public interface EmpleadoService {

    List<Empleado> findAllEmpleados();
    Empleado saveEmpleado(Empleado empleado);
    Empleado findEmpleadoById(String id);
    Empleado updateEmpleado(Empleado empleado, String id);
    void deleteEmpleado(String id);


}

package com.jotacode.apigym.model.data;

import com.jotacode.apigym.model.entity.Cuenta;
import com.jotacode.apigym.model.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

     Optional<Empleado> findByCuenta(Cuenta cuenta);
}

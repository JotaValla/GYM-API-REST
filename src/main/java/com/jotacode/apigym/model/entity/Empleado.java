package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empleados")
@AllArgsConstructor @NoArgsConstructor
public class Empleado extends Persona {

    @Column(name = "rol", length = 50)
    @Getter @Setter
    private String rol;

    @OneToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

}

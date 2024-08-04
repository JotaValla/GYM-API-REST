package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "empleados")
@AllArgsConstructor @NoArgsConstructor
public class Empleado extends Persona {

    @Column(name = "rol", length = 50)
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Rol rol;


    @OneToOne
    @JoinColumn(name = "id_cuenta")
    @Getter @Setter
    private Cuenta cuenta;

}

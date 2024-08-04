package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "clientes")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Cliente extends Persona {

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

}

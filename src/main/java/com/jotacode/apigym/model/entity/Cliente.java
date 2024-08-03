package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@NoArgsConstructor @AllArgsConstructor
public class Cliente extends Persona {

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

    // Getters y setters
}

package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membresias")
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Membresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membresia")
    private Long idMembresia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_membresia", nullable = false, length = 50)
    @NotNull(message = "Tipo de membresia es requerido")
    private TipoMembresia tipoMembresia;

    @Column(name = "precio", nullable = false)
    @NotNull(message = "Precio de membresia es requerido")
    private float precio;

    @Column(name = "duracion")
    @NotNull(message = "Duración de membresia es requerida")
    private int duracion;

    // Relación con Cliente
    @OneToOne(mappedBy = "membresia", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Cliente cliente;

    // Getters y setters
}

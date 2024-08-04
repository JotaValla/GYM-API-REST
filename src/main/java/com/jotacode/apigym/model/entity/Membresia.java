package com.jotacode.apigym.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "membresias")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "fecha_creacion", nullable = false)
    @NotNull(message = "Fecha de creación es requerida")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_expiracion", nullable = false)
    @NotNull(message = "Fecha de expiración es requerida")
    private LocalDate fechaExpiracion;

    @Column(name = "estado_membresia", nullable = false)
    @NotNull(message = "Estado de membresia es requerido")
    private Boolean estadoMembresia = true;

    @OneToOne(mappedBy = "membresia", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @Getter @Setter
    private Cliente cliente;


}

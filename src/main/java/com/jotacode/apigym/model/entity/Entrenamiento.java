package com.jotacode.apigym.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entrenamientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEntrenamiento")
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenamiento")
    private Long idEntrenamiento;

    @Column(name = "nombre", length = 75)
    @NotBlank(message = "Nombre es requerido")
    @NotNull(message = "Nombre es requerido")
    private String nombre;

    @Column(name = "descripcion", length = 150)
    @NotBlank(message = "Descripción es requerida")
    @NotNull(message = "Descripción es requerida")
    private String descripcion;

    @Column(name = "duracion")
    @NotNull(message = "Duración es requerida")
    private int duracion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

}

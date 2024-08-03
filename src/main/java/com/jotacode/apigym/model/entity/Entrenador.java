package com.jotacode.apigym.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "entrenadores")
@AllArgsConstructor @NoArgsConstructor
public class Entrenador extends Persona {

    @Column(name = "especialidad", length = 50)
    @NotBlank(message = "Especialidad es requerida")
    @NotNull(message = "Especialidad es requerida")
    @Getter @Setter
    private String especialidad;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Entrenamiento> entrenamientos;

}

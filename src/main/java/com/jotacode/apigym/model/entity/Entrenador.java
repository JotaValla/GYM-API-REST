package com.jotacode.apigym.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "entrenadores")
@AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cedula")
public class Entrenador extends Persona {

    @Column(name = "especialidad", length = 50)
    @NotBlank(message = "Especialidad es requerida")
    @NotNull(message = "Especialidad es requerida")
    @Getter @Setter
    private String especialidad;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Getter @Setter
    private List<Entrenamiento> entrenamientos;

    public void addEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.add(entrenamiento);
        entrenamiento.setEntrenador(this);
    }

    public void removeEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientos.remove(entrenamiento);
        entrenamiento.setEntrenador(null);
    }

}

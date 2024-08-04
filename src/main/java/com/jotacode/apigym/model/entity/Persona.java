package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@MappedSuperclass
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public  class Persona {

    @Id
    @Column(name = "cedula", length = 10)
    @Pattern(regexp = "\\d{10}", message = "Cedula debe tener 10 dígitos numéricos")
    private String cedula;

    @Column(name = "nombre", length = 50)
    @NotBlank(message = "Nombre es requerido")
    @NotNull(message = "Nombre es requerido")
    private String nombre;

    @Column(name = "apellido", length = 50)
    @NotBlank(message = "Apellido es requerido")
    @NotNull(message = "Apellido es requerido")
    private String apellido;

    @Column(name = "telefono", length = 10)
    @NotBlank(message = "Telefono es requerido")
    @NotNull(message = "Telefono es requerido")
    @Size(min = 10, max = 10, message = "Telefono debe tener 10 dígitos")
    private String telefono;

    @Column(name = "correo", length = 50)
    @NotBlank(message = "Correo es requerido")
    @NotNull(message = "Correo es requerido")
    private String correo;

    @Column(name = "fecha_nacimiento")
    @Past(message = "Fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @Column(name = "activo")
    @NotNull(message = "Activo es requerido")
    private Boolean activo = true;

    @Embedded
    private Direccion direccion;
}

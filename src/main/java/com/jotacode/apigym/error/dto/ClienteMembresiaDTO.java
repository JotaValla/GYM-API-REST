package com.jotacode.apigym.error.dto;

import com.jotacode.apigym.model.entity.TipoMembresia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ClienteMembresiaDTO {

    @Pattern(regexp = "\\d{10}", message = "Cedula debe tener 10 dígitos numéricos")
    private String cedula;

    @NotNull(message = "ID de membresia es requerido")
    private Long idMembresia;

    @NotNull(message = "Tipo de membresia es requerido")
    private TipoMembresia tipoMembresia;

    public ClienteMembresiaDTO(String cedula, Long idMembresia, TipoMembresia tipoMembresia) {
        this.cedula = cedula;
        this.idMembresia = idMembresia;
        this.tipoMembresia = tipoMembresia;
    }

    // Getters and setters

    public @Pattern(regexp = "\\d{10}", message = "Cedula debe tener 10 dígitos numéricos") String getCedula() {
        return cedula;
    }

    public void setCedula(@Pattern(regexp = "\\d{10}", message = "Cedula debe tener 10 dígitos numéricos") String cedula) {
        this.cedula = cedula;
    }

    public @NotNull(message = "ID de membresia es requerido") Long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(@NotNull(message = "ID de membresia es requerido") Long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public @NotNull(message = "Tipo de membresia es requerido") TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(@NotNull(message = "Tipo de membresia es requerido") TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
}

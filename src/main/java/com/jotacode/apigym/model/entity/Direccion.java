package com.jotacode.apigym.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Embeddable
public class Direccion {

    @NotBlank(message = "La ciudad es requerida")
    private String ciudad;

    @NotBlank(message = "La calle principal es requerida")
    private String callePrincipal;

    @NotBlank(message = "La calle secundaria es requerida")
    private String calleSecundaria;
}

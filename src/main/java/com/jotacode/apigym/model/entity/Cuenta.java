package com.jotacode.apigym.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "cuenta",
        uniqueConstraints = @UniqueConstraint(
                name = "username_unique",
                columnNames = "username"
        )
)
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long idCuenta;

    @Column(name = "username", nullable = false, length = 50)
    @NotBlank(message = "Username es requerido")
    @NotNull(message = "Username es requerido")
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    @NotBlank(message = "Password es requerido")
    @NotNull(message = "Password es requerido")
    @Size(min = 8, max = 50, message = "Password debe tener entre 8 y 50 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password debe contener al menos un número, una letra mayúscula, una letra minúscula y un carácter especial"
    )
    private String password;

}

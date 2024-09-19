package com.vocacional.orientacionvocacional.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @Email(message = "Correo no válido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    private String resetPasswordToken;

    public @NotBlank(message = "El nombre es obligatorio") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "El nombre es obligatorio") String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El apellido es obligatorio") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "El apellido es obligatorio") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Correo no válido") @NotBlank(message = "El correo es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Correo no válido") @NotBlank(message = "El correo es obligatorio") String email) {
        this.email = email;
    }

    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password) {
        this.password = password;
    }


    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }
}
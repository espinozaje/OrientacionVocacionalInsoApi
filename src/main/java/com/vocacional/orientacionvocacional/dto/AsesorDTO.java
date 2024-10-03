package com.vocacional.orientacionvocacional.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AsesorDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @Email(message = "Correo no válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    // private ERole role;

    public @NotBlank(message = "El nombre es obligatorio") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "El nombre es obligatorio") String firstName) {
        this.firstName = firstName;
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

    public @NotBlank(message = "La especialidad es obligatoria") String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(@NotBlank(message = "La especialidad es obligatoria") String especialidad) {
        this.especialidad = especialidad;
    }
}

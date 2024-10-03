package com.vocacional.orientacionvocacional.model.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "Asesores")
public class Asesor extends User{
    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    public @NotBlank(message = "La especialidad es obligatoria") String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(@NotBlank(message = "La especialidad es obligatoria") String especialidad) {
        this.especialidad = especialidad;
    }
}

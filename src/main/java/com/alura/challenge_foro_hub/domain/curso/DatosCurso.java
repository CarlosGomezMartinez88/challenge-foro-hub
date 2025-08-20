package com.alura.challenge_foro_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosCurso(
        @NotBlank(message = "El nombre del curso es obligatorio")
        String nombre,

        @NotBlank(message = "La categoría del curso es obligatoria")
        String categoria
) {}

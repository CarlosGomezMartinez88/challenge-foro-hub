package com.alura.challenge_foro_hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuestas(
        @NotNull(message = "El ID de la respuesta es obligatorio")
        Long id,

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje
) {}

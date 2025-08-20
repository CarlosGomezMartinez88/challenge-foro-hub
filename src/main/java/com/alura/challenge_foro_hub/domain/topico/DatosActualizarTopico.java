package com.alura.challenge_foro_hub.domain.topico;

import com.alura.challenge_foro_hub.domain.curso.DatosCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String mensaje,
        StatusTopicos status,
        @Valid DatosCurso curso
) {}

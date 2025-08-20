package com.alura.challenge_foro_hub.domain.respuesta.repository;

import com.alura.challenge_foro_hub.domain.respuesta.Respuesta;
import com.alura.challenge_foro_hub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByTopicoAndActivoTrue(Topico topico);
}

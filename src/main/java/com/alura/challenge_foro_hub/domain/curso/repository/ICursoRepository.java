package com.alura.challenge_foro_hub.domain.curso.repository;

import com.alura.challenge_foro_hub.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNombre(String nombre);
}

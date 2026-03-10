package com.aluracursos.foro_hub.domain.topico;

import com.aluracursos.foro_hub.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Long cursoId,
        StatusTopico status

) {
}

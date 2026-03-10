package com.aluracursos.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosRepuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status
) {
}

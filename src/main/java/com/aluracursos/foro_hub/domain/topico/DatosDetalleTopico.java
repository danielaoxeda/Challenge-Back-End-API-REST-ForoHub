package com.aluracursos.foro_hub.domain.topico;

import com.aluracursos.foro_hub.domain.curso.Curso;
import com.aluracursos.foro_hub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaCreacion,
        StatusTopico status
) {

    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion(),
                topico.getStatus()
        );
    }
}

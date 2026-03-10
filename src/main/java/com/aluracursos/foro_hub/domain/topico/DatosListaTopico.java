package com.aluracursos.foro_hub.domain.topico;

import com.aluracursos.foro_hub.domain.usuario.DatosAutor;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        StatusTopico status,
        DatosAutor autor,
        LocalDateTime fechaCreacion
) {

    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                new DatosAutor(topico.getAutor()),
                topico.getFechaCreacion()
        );
    }
}

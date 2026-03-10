package com.aluracursos.foro_hub.domain.usuario;

public record DatosAutor(
        Long id,
        String nombre,
        String email
) {
    public DatosAutor(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
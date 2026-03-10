package com.aluracursos.foro_hub.controllers;

import com.aluracursos.foro_hub.domain.curso.Curso;
import com.aluracursos.foro_hub.domain.curso.CursoRepository;
import com.aluracursos.foro_hub.domain.topico.*;
import com.aluracursos.foro_hub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private PagedResourcesAssembler<DatosListaTopico> pagedResourcesAssembler;
    @Autowired
    private DatosListaTopicoAssembler datosListaTopicoAssembler;

    // Registrar un topico
    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        var autor = usuarioRepository.findById(datos.autorId()).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        var curso = cursoRepository.findById(datos.cursoId()).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }

        var topico = new Topico(datos, autor, curso);

        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }
    // Mostrar todos los topicos
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DatosListaTopico>>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<DatosListaTopico> pagina = topicoRepository
                        .findByStatusNot(StatusTopico.CERRADO, paginacion)
                        .map(DatosListaTopico::new);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(pagina, datosListaTopicoAssembler)
        );
    }
    // Detallando un topico
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }
    // Actualizar un topico
    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        var topico = topicoRepository.getReferenceById(datos.id());
        Curso curso = null;

        if (datos.cursoId() != null) {
            curso = cursoRepository.getReferenceById(datos.cursoId());
        }

        topico.actualizarInformacion(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    // Eliminar un topico
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        topico.eliminar();

        return ResponseEntity.noContent().build();
    }
}

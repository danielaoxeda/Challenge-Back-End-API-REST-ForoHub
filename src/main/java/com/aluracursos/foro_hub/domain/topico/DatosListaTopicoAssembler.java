package com.aluracursos.foro_hub.domain.topico;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DatosListaTopicoAssembler implements RepresentationModelAssembler<DatosListaTopico, EntityModel<DatosListaTopico>> {


    @Override
    public EntityModel<DatosListaTopico> toModel(DatosListaTopico datosListaTopico) {
        return EntityModel.of(datosListaTopico);
    }
}

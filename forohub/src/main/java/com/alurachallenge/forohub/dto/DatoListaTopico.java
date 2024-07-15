package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.models.Curso;
import com.alurachallenge.forohub.models.Topico;

public record DatoListaTopico(
        Long id,
        String titulo,
        String mensaje,
        Curso curso
) {
    public DatoListaTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}

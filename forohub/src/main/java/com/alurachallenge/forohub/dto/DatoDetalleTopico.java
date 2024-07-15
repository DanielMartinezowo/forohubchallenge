package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.models.Curso;
import com.alurachallenge.forohub.models.Topico;

import java.time.LocalDateTime;

public record DatoDetalleTopico(
            String titulo,
            String mensaje,
            LocalDateTime fechaCreacion,
            Curso curso
)
{
    public DatoDetalleTopico (Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getCurso());
    }
}

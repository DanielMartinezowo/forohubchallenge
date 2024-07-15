package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.models.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatoRegistroTopico(
            @NotBlank
            String titulo,
            @NotBlank
            String mensaje,
            @NotNull
            @Valid
            Curso curso
) {
}

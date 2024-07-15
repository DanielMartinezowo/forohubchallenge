package com.alurachallenge.forohub.models;


import com.alurachallenge.forohub.dto.DatoActualizarTopico;
import com.alurachallenge.forohub.dto.DatoRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatoRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.curso = datosRegistroTopico.curso();
    }
    public void actualizarInformacion(DatoActualizarTopico datosActualizarTopico) {
        if(datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }

        if(datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }
}

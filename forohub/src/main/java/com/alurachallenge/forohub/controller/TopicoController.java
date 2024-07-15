package com.alurachallenge.forohub.controller;


import com.alurachallenge.forohub.dto.DatoActualizarTopico;
import com.alurachallenge.forohub.dto.DatoDetalleTopico;
import com.alurachallenge.forohub.dto.DatoListaTopico;
import com.alurachallenge.forohub.dto.DatoRegistroTopico;
import com.alurachallenge.forohub.models.Topico;
import com.alurachallenge.forohub.models.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
    public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatoRegistroTopico datoRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        var topico = new Topico(datoRegistroTopico);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatoDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatoListaTopico>> datoListaTopicos(@PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {
        var page = topicoRepository.findAll(pageable).map(DatoListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatoActualizarTopico datoActualizarTopico) {
        var topico = topicoRepository.getReferenceById(datoActualizarTopico.id());
        topico.actualizarInformacion(datoActualizarTopico);
        return ResponseEntity.ok(new DatoDetalleTopico(topico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatoDetalleTopico(topico));
    }
}

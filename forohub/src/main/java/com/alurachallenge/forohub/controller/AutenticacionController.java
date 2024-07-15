package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.dto.DatoAutenticacionUser;
import com.alurachallenge.forohub.dto.DatoJWToken;
import com.alurachallenge.forohub.infra.security.TokenService;
import com.alurachallenge.forohub.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatoAutenticacionUser datosAutenticacion) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(), datosAutenticacion.password());
        var autenticacion = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatoJWToken(tokenJWT));
    }
}

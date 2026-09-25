package br.com.josecarlosn.auth.controller;

import br.com.josecarlosn.auth.dto.request.AuthenticationRequestDTO;
import br.com.josecarlosn.auth.dto.request.RegisterRequestDTO;

import br.com.josecarlosn.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequestDTO dto){
        authService.login(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO dto){
        authService.register(dto);
        return ResponseEntity.ok("Registrado com sucesso!");
    }
}


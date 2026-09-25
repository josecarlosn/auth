package br.com.josecarlosn.auth.dto.request;

import br.com.josecarlosn.auth.entity.UserRole;

public record RegisterRequestDTO(String login, String password, String name, UserRole role) { }

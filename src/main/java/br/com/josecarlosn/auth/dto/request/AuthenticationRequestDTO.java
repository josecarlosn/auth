package br.com.josecarlosn.auth.dto.request;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthenticationRequestDTO(String login, String password) {
}

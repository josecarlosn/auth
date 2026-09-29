package br.com.josecarlosn.auth.service;

import br.com.josecarlosn.auth.dto.request.AuthenticationRequestDTO;
import br.com.josecarlosn.auth.dto.request.LoginResponseDTO;
import br.com.josecarlosn.auth.dto.request.RegisterRequestDTO;
import br.com.josecarlosn.auth.entity.User;
import br.com.josecarlosn.auth.exception.UserException;
import br.com.josecarlosn.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponseDTO login(AuthenticationRequestDTO dto){
        var userNamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) Objects.requireNonNull(auth.getPrincipal()));
        return new LoginResponseDTO(token);

    }
    public void register(RegisterRequestDTO dto){
        if (userRepository.findByLogin(dto.login()) != null) throw new UserException("User already exists.");
        var passwordEncoded = passwordEncoder.encode(dto.password());
        User user = new User(dto.login(), passwordEncoded, dto.name(), dto.role());
        userRepository.save(user);
    }
}

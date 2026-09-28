package br.com.josecarlosn.auth.service;

import br.com.josecarlosn.auth.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-system")
                    .withExpiresAt(generateExpirationDate())
                    .withSubject(user.getLogin())
                    .sign(algorithm);
        }
        catch(JWTCreationException exception){
            throw new RuntimeException("Error while generating token: ",exception);
        }
    }
    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-system")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){throw new RuntimeException("Invalid token: ",exception);}
    }
    public Instant generateExpirationDate(){
        return LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00"));
    }
}

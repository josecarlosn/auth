package br.com.josecarlosn.auth.config;

import br.com.josecarlosn.auth.entity.User;
import br.com.josecarlosn.auth.repository.UserRepository;
import br.com.josecarlosn.auth.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException
    {
        var token = recoverToken(request);
        if (token != null){
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByLogin(login);
            var authentication = new UsernamePasswordAuthenticationToken(login, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
    public String recoverToken(HttpServletRequest request){
        var header = request.getHeader("Authorization");
        if (header == null){return null;}
        return header.replace("Bearer ", "");
    }

}

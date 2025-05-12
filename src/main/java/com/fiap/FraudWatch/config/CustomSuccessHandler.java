package com.fiap.FraudWatch.config;

import com.fiap.FraudWatch.model.TipoUsuario;
import com.fiap.FraudWatch.model.Usuario;
import com.fiap.FraudWatch.repository.TipoUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Usuario usuario = (Usuario) authentication.getPrincipal();
        if ("Administrador".equalsIgnoreCase(usuario.getTipoUsuario().getDescricao())) {
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/home");
        }



    }
}

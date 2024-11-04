package com.fiap.FraudWatch.config;

import com.fiap.FraudWatch.service.TipoUsuarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InicializadorDados {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @PostConstruct
    public void Init() {
        // Inicializa os tipos de usuário
        tipoUsuarioService.inicializarTiposUsuario();
    }
}

package com.fiap.FraudWatch.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String FILA_USUARIO_EVENTO = "fila.usuario.evento";

    @Bean
    public Queue filaUsuarioEvento() {
        return new Queue(FILA_USUARIO_EVENTO, true, false, false);
    }
}
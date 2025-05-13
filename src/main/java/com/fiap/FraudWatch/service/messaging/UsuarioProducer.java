package com.fiap.FraudWatch.service.messaging;

import com.fiap.FraudWatch.config.RabbitMQConfig;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProducer {

    private final RabbitTemplate rabbitTemplate;

    public UsuarioProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEventoUsuario(UsuarioResponse usuario, String acao) {
        String mensagem = "[USUÁRIO - " + acao.toUpperCase() + "] Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail();
        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_USUARIO_EVENTO, mensagem);
        System.out.println("Evento enviado: " + mensagem);
    }
}

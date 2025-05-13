package com.fiap.FraudWatch.service.messaging;

import com.fiap.FraudWatch.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConsumer {

    @RabbitListener(queues = RabbitMQConfig.FILA_USUARIO_EVENTO)
    public void processarEvento(String mensagem) {
        System.out.println(">> Evento recebido da fila: " + mensagem);
        // Aqui você pode simular um log, notificação, IA, etc.
    }
}

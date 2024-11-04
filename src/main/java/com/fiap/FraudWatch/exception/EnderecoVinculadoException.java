package com.fiap.FraudWatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EnderecoVinculadoException extends RuntimeException {
    public EnderecoVinculadoException(String message) {
        super(message);
    }
}

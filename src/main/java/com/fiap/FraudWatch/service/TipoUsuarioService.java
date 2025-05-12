package com.fiap.FraudWatch.service;

import com.fiap.FraudWatch.model.TipoUsuario;
import com.fiap.FraudWatch.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Transactional
    public void inicializarTiposUsuario() {
        // Verifica se o tipo de usuário "Dentista" existe; se não, cria.
        if (tipoUsuarioRepository.findByDescricao("Dentista").isEmpty()) {
            TipoUsuario dentista = new TipoUsuario();
            dentista.setDescricao("Dentista");
            tipoUsuarioRepository.save(dentista);
        }

        // Verifica se o tipo de usuário "Paciente" existe; se não, cria.
        if (tipoUsuarioRepository.findByDescricao("Paciente").isEmpty()) {
            TipoUsuario paciente = new TipoUsuario();
            paciente.setDescricao("Paciente");
            tipoUsuarioRepository.save(paciente);
        }

        // Verifica se o tipo de usuário "Analista" existe; se não, cria.
        if (tipoUsuarioRepository.findByDescricao("Analista").isEmpty()) {
            TipoUsuario analista = new TipoUsuario();
            analista.setDescricao("Analista");
            tipoUsuarioRepository.save(analista);
        }
        if (tipoUsuarioRepository.findByDescricao("Administrador").isEmpty()) {
            TipoUsuario administrador = new TipoUsuario();
            administrador.setDescricao("Administrador");
            tipoUsuarioRepository.save(administrador);
        }
    }
}

package com.fiap.FraudWatch.service;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioResponse;
import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.exception.CpfJaCadastradoException;
import com.fiap.FraudWatch.exception.EmailJaCadastradoException;
import com.fiap.FraudWatch.model.Endereco;
import com.fiap.FraudWatch.model.TipoUsuario;
import com.fiap.FraudWatch.model.Usuario;
import com.fiap.FraudWatch.repository.EnderecoRepository;
import com.fiap.FraudWatch.repository.TipoUsuarioRepository;
import com.fiap.FraudWatch.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final EnderecoService enderecoService;
    @Autowired
    private final EnderecoServiceAsync enderecoServiceAsync;
    @Autowired
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EnderecoService enderecoService, EnderecoServiceAsync enderecoServiceAsync, TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoService = enderecoService;
        this.enderecoServiceAsync = enderecoServiceAsync;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public Usuario requestToUsuario(UsuarioRequest usuarioRequest, EnderecoRequest enderecoRequest) {

        if (usuarioRepository.findByCpf(usuarioRequest.cpf()).isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        if (usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setSobrenome(usuarioRequest.sobrenome());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(usuarioRequest.senha());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setDataNascimento(usuarioRequest.dataNascimento());
        usuario.setTelefone(usuarioRequest.telefone());
        usuario.setDataCadastro(LocalDateTime.now());
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(usuarioRequest.tipoUsuarioid())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuário não encontrado"));
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setEndereco(enderecoService.requestToEndereco(enderecoRequest, enderecoServiceAsync.obterEnderecoPorCep(enderecoRequest.cep())));
        return usuario;

    }

    public UsuarioResponse usuarioToResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getTelefone(),
                usuario.getDataCadastro(),
                usuario.getTipoUsuario().getDescricao(),
                enderecoService.enderecoToResponse(usuario.getEndereco())
        );
    }

    public Usuario updateUsuario(Long id, UsuarioRequest usuarioRequest, EnderecoRequest enderecoRequest) {
        // Buscar o usuário existente no banco de dados
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Verificar se o CPF está sendo alterado e se já está cadastrado por outro usuário
        if (!usuarioExistente.getCpf().equals(usuarioRequest.cpf()) && usuarioRepository.findByCpf(usuarioRequest.cpf()).isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        // Verificar se o email está sendo alterado e se já está cadastrado por outro usuário
        if (!usuarioExistente.getEmail().equals(usuarioRequest.email()) && usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        // Atualizar os campos do usuário existente com os novos valores
        usuarioExistente.setNome(usuarioRequest.nome());
        usuarioExistente.setSobrenome(usuarioRequest.sobrenome());
        usuarioExistente.setEmail(usuarioRequest.email());
        usuarioExistente.setSenha(usuarioRequest.senha());
        usuarioExistente.setCpf(usuarioRequest.cpf());
        usuarioExistente.setDataNascimento(usuarioRequest.dataNascimento());
        usuarioExistente.setTelefone(usuarioRequest.telefone());

        // Atualizar o tipo de usuário
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(usuarioRequest.tipoUsuarioid())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuário não encontrado"));
        usuarioExistente.setTipoUsuario(tipoUsuario);

        // Atualizar o endereço
        usuarioExistente.setEndereco(enderecoService.requestToEndereco(enderecoRequest, enderecoServiceAsync.obterEnderecoPorCep(enderecoRequest.cep())));

        // O campo DataCadastro não será alterado

        // Retornar o usuário atualizado
        return usuarioRepository.save(usuarioExistente);
    }





}

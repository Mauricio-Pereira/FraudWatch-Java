package com.fiap.FraudWatch.service;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioResponseDTO;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.Future;

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
    @Autowired
    private final EnderecoRepository enderecoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          EnderecoService enderecoService,
                          EnderecoServiceAsync enderecoServiceAsync,
                          TipoUsuarioRepository tipoUsuarioRepository,
                          EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoService = enderecoService;
        this.enderecoServiceAsync = enderecoServiceAsync;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Usuario RequestToUsuario(UsuarioRequest usuarioRequest,
                                    EnderecoRequest enderecoRequest) {

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
        TipoUsuario tipoUsuario =
                tipoUsuarioRepository.findById(usuarioRequest.tipoUsuarioid())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Tipo de usuário não encontrado"));
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setEndereco(enderecoService.RequestToEndereco(enderecoRequest,
                enderecoServiceAsync.ObterEnderecoPorCepAsync(
                        enderecoRequest.cep())));
        return usuario;

    }

    public UsuarioResponseDTO usuarioToResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
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
                enderecoService.EnderecoToResponseDTO(usuario.getEndereco())

        );
    }

    public UsuarioResponse usuarioToResponse(Usuario usuario, Link link) {
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
                enderecoService.EnderecoToResponseDTO(usuario.getEndereco()),
                link
        );
    }

    public Usuario UpdateUsuario(Long id, UsuarioRequest usuarioRequest,
                                 EnderecoRequest enderecoRequest) {
        // Buscar o usuário existente no banco de dados
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuário não encontrado"));

        // Verificar se o CPF está sendo alterado e se já está cadastrado por outro usuário
        if (!usuarioExistente.getCpf().equals(usuarioRequest.cpf()) &&
                usuarioRepository.findByCpf(usuarioRequest.cpf()).isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        // Verificar se o email está sendo alterado e se já está cadastrado por outro usuário
        if (!usuarioExistente.getEmail().equals(usuarioRequest.email()) &&
                usuarioRepository.findByEmail(usuarioRequest.email())
                        .isPresent()) {
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
        TipoUsuario tipoUsuario =
                tipoUsuarioRepository.findById(usuarioRequest.tipoUsuarioid())
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Tipo de usuário não encontrado"));
        usuarioExistente.setTipoUsuario(tipoUsuario);

        // Atualizar o endereço
        usuarioExistente.setEndereco(
                enderecoService.RequestToEndereco(enderecoRequest,
                        enderecoServiceAsync.ObterEnderecoPorCepAsync(
                                enderecoRequest.cep())));

        // O campo DataCadastro não será alterado

        // Retornar o usuário atualizado
        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional // Anotação para controlar a transação
    public Usuario createUsuarioWithProcedure(UsuarioRequest usuarioRequest) {
        EnderecoRequest enderecoRequest = usuarioRequest.endereco();
        Endereco endereco = null;

        try {
            // Cadastra o endereço
            endereco = enderecoService.createEnderecoUsingProcedure(
                    enderecoRequest,
                    enderecoServiceAsync.ObterEnderecoPorCepAsync(
                            enderecoRequest.cep())
            );

            // Configurar e executar a stored procedure para criar o usuário
            StoredProcedureQuery storedProcedure =
                    entityManager.createStoredProcedureQuery("create_usuario");

            // Registrar os parâmetros de entrada
            storedProcedure.registerStoredProcedureParameter("p_email",
                    String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_senha",
                    String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_nome",
                    String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_sobrenome",
                    String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_cpf",
                    String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(
                    "p_data_nascimento", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_telefone",
                    String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_id_endereco",
                    Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(
                    "p_id_tipo_usuario", Long.class, ParameterMode.IN);
            // Registrar o parâmetro de saída (se a procedure retornar o ID do usuário)
            storedProcedure.registerStoredProcedureParameter("p_id_usuario",
                    Long.class, ParameterMode.OUT);

            // Definir os valores dos parâmetros de entrada
            storedProcedure.setParameter("p_email", usuarioRequest.email());
            storedProcedure.setParameter("p_senha", usuarioRequest.senha());
            storedProcedure.setParameter("p_nome", usuarioRequest.nome());
            storedProcedure.setParameter("p_sobrenome",
                    usuarioRequest.sobrenome());
            storedProcedure.setParameter("p_cpf", usuarioRequest.cpf());
            storedProcedure.setParameter("p_data_nascimento",
                    Date.valueOf(usuarioRequest.dataNascimento()));
            storedProcedure.setParameter("p_telefone",
                    usuarioRequest.telefone());
            storedProcedure.setParameter("p_id_endereco", endereco.getId());
            Long idTipoUsuario = usuarioRequest.tipoUsuarioid();
            storedProcedure.setParameter("p_id_tipo_usuario", idTipoUsuario);

            // Executar a stored procedure
            storedProcedure.execute();

            // Obter o valor do parâmetro de saída (ID do usuário)
            Long idUsuario = (Long) storedProcedure.getOutputParameterValue(
                    "p_id_usuario");

            // Criar o objeto Usuario com o ID gerado
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            usuario.setNome(usuarioRequest.nome());
            usuario.setSobrenome(usuarioRequest.sobrenome());
            usuario.setEmail(usuarioRequest.email());
            usuario.setSenha(usuarioRequest.senha());
            usuario.setCpf(usuarioRequest.cpf());
            usuario.setDataNascimento(usuarioRequest.dataNascimento());
            usuario.setTelefone(usuarioRequest.telefone());
            usuario.setEndereco(endereco);

            // Buscar o TipoUsuario pelo ID fornecido e atribuir ao usuário
            TipoUsuario tipoUsuario =
                    tipoUsuarioRepository.findById(idTipoUsuario)
                            .orElseThrow(() -> new RuntimeException(
                                    "Tipo de usuário não encontrado com o ID: " +
                                            idTipoUsuario));
            usuario.setTipoUsuario(tipoUsuario);

            return usuario; // Retorna o usuário cadastrado
        } catch (Exception e) {
            // Caso ocorra uma exceção, o Spring automaticamente reverterá a transação
            // e podemos excluir o endereço se ele foi criado
            if (endereco != null) {
                // Aqui você pode chamar um método para remover o endereço
                enderecoService.deleteEnderecoUsingProcedure(endereco.getId());
            }
            throw new RuntimeException(
                    "Erro ao cadastrar o usuário: " + e.getMessage(), e);
        }
    }


    @Transactional
    public Usuario updateUsuarioWithProcedure(Long id, UsuarioRequest usuarioRequest) {
        // 1. Verificar se o usuário existe
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);

        if (usuarioExistenteOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Usuario usuarioExistente = usuarioExistenteOpt.get();

        // 2. Verificar se o CPF está sendo alterado e se já está cadastrado por outro usuário
        if (!usuarioExistente.getCpf().equals(usuarioRequest.cpf()) &&
                usuarioRepository.findByCpf(usuarioRequest.cpf()).isPresent()) {
            throw new CpfJaCadastradoException("CPF já cadastrado");
        }

        // 3. Verificar se o email está sendo alterado e se já está cadastrado por outro usuário
        if (!usuarioExistente.getEmail().equals(usuarioRequest.email()) &&
                usuarioRepository.findByEmail(usuarioRequest.email()).isPresent()) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        // 4. Atualizar o endereço utilizando o EnderecoService
        EnderecoRequest enderecoRequest = usuarioRequest.endereco();
        Optional<Endereco> enderecoToUpdateOpt = enderecoRepository.findEnderecoByIdUsuario(id);
        if (enderecoToUpdateOpt.isEmpty()) {
            throw new IllegalArgumentException("Endereço não encontrado para o usuário com ID: " + id);
        }
        Endereco enderecoToUpdate = enderecoToUpdateOpt.get();

        Endereco enderecoAtualizado = enderecoService.updateEnderecoUsingProcedure(
                enderecoToUpdate.getId(),
                enderecoRequest,
                enderecoServiceAsync.ObterEnderecoPorCepAsync(enderecoRequest.cep())
        );

        enderecoAtualizado.setId(enderecoToUpdate.getId());
        // Verificar se o ID do endereço atualizado não é NULL
        if (enderecoAtualizado.getId() == null) {
            throw new IllegalArgumentException("Falha ao atualizar o endereço. ID do endereço é NULL.");
        }

        try {
            // 5. Configurar e executar a stored procedure para atualizar o usuário
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("update_usuario");

            // Registrar os parâmetros de entrada
            storedProcedure.registerStoredProcedureParameter("p_id_usuario", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_senha", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_nome", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_sobrenome", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_cpf", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_data_nascimento", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_telefone", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_id_endereco", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_id_tipo_usuario", Long.class, ParameterMode.IN);

            // Definir os valores dos parâmetros de entrada
            storedProcedure.setParameter("p_id_usuario", id);
            storedProcedure.setParameter("p_email", usuarioRequest.email());
            storedProcedure.setParameter("p_senha", usuarioRequest.senha());
            storedProcedure.setParameter("p_nome", usuarioRequest.nome());
            storedProcedure.setParameter("p_sobrenome", usuarioRequest.sobrenome());
            storedProcedure.setParameter("p_cpf", usuarioRequest.cpf());
            storedProcedure.setParameter("p_data_nascimento", Date.valueOf(usuarioRequest.dataNascimento()));
            storedProcedure.setParameter("p_telefone", usuarioRequest.telefone());
            storedProcedure.setParameter("p_id_endereco", enderecoAtualizado.getId());
            storedProcedure.setParameter("p_id_tipo_usuario", usuarioRequest.tipoUsuarioid());

            // Executar a stored procedure
            storedProcedure.execute();

            // 6. Buscar o usuário atualizado
            Usuario usuarioAtualizado = usuarioRepository.findById(id).get();
            // 7. Retornar o usuário atualizado
            return usuarioAtualizado;

        } catch (Exception e) {
            // Em caso de erro, a transação será revertida automaticamente pelo Spring
            throw new RuntimeException("Erro ao atualizar o usuário: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deleteUsuarioWithProcedure(Long id) {
        // 1. Verificar se o usuário existe
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        try {
            var enderecoID = enderecoRepository.findEnderecoByIdUsuario(id).get();

            usuarioRepository.deleteUsuarioById(id);
            enderecoService.deleteEnderecoUsingProcedure(enderecoID.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o usuário: " + e.getMessage(), e);
        }
    }




}

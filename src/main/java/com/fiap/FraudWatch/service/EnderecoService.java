package com.fiap.FraudWatch.service;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponse;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponseDTO;
import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.exception.EnderecoVinculadoException;
import com.fiap.FraudWatch.model.*;
import com.fiap.FraudWatch.repository.BairroRepository;
import com.fiap.FraudWatch.repository.CidadeRepository;
import com.fiap.FraudWatch.repository.EnderecoRepository;
import com.fiap.FraudWatch.repository.EstadoRepository;
import com.fiap.FraudWatch.repository.RegiaoRepository;
import com.fiap.FraudWatch.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private final BairroRepository bairroRepository;
    @Autowired
    private final CidadeRepository cidadeRepository;
    @Autowired
    private final EstadoRepository estadoRepository;
    @Autowired
    private final RegiaoRepository regiaoRepository;
    @Autowired
    private final EnderecoRepository enderecoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public EnderecoService(BairroRepository bairroRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository, RegiaoRepository regiaoRepository, EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository) {
        this.bairroRepository = bairroRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.regiaoRepository = regiaoRepository;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
    }




    public Endereco RequestToEndereco(EnderecoRequest enderecoRequest, Future<ViaCepResponse> futureViaCepResponse) {
        ViaCepResponse viaCepResponse;
        try {
            // Aguarda a conclusão do Future e obtém a resposta do ViaCep
            viaCepResponse = futureViaCepResponse.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erro ao obter ViaCepResponse", e);
        }

        // Verifica e cria Regiao, Estado, Cidade e Bairro com base nos dados do ViaCep e EnderecoRequest
        Regiao regiaoEntity = regiaoRepository.findByNomeRegiao(viaCepResponse.regiao())
                .orElseGet(() -> regiaoRepository.save(new Regiao(viaCepResponse.regiao() != null ? viaCepResponse.regiao() : capitalizeWords(enderecoRequest.regiao()))));

        Estado estadoEntity = estadoRepository.findByNomeEstado(viaCepResponse.estado())
                .orElseGet(() -> estadoRepository.save(new Estado(viaCepResponse.estado() != null ? viaCepResponse.estado() : capitalizeWords(enderecoRequest.estado()))));

        Cidade cidadeEntity = cidadeRepository.findByNomeCidade(viaCepResponse.localidade())
                .orElseGet(() -> {
                    Cidade novaCidade = new Cidade(viaCepResponse.localidade() != null ? viaCepResponse.localidade() : capitalizeWords(enderecoRequest.cidade()));
                    novaCidade.setEstado(estadoEntity);
                    return cidadeRepository.save(novaCidade);
                });

        String nomeBairroParaBusca = capitalizeWords(
                (viaCepResponse.bairro() != null && !viaCepResponse.bairro().isEmpty())
                        ? viaCepResponse.bairro()
                        : (enderecoRequest.bairro() != null && !enderecoRequest.bairro().isEmpty())
                        ? enderecoRequest.bairro()
                        : "Bairro Padrão" // Valor padrão se ambos estiverem vazios
        );


        Bairro bairroEntity = bairroRepository.findByNomeBairroAndCidade(nomeBairroParaBusca, cidadeEntity)
                .orElseGet(() -> {
                    String nomeBairro = (viaCepResponse.bairro() != null && !viaCepResponse.bairro().isEmpty()) ? viaCepResponse.bairro() : capitalizeWords(enderecoRequest.bairro());
                    Bairro novoBairro = new Bairro(nomeBairro);
                    novoBairro.setCidade(cidadeEntity);
                    return bairroRepository.save(novoBairro);
                });

        String logradouro = (viaCepResponse.logradouro() != null && !viaCepResponse.logradouro().isEmpty())
                ? capitalizeWords(viaCepResponse.logradouro())
                : (enderecoRequest.logradouro() != null && !enderecoRequest.logradouro().isEmpty())
                ? capitalizeWords(enderecoRequest.logradouro())
                : "Logradouro Padrão";

        bairroEntity.setCidade(cidadeEntity);
        cidadeEntity.setEstado(estadoEntity);
        estadoEntity.setRegiao(regiaoEntity);

        Endereco endereco = new Endereco();
        endereco.setCep(viaCepResponse.cep());
        endereco.setLogradouro(logradouro);
        endereco.setBairro(bairroEntity);
        endereco.setComplemento(enderecoRequest.complemento());
        endereco.setNumero(enderecoRequest.numero());

        return endereco;
    }

    // Método auxiliar para capitalizar as palavras
        private String capitalizeWords(String input) {
            if (input == null || input.isEmpty()) {
                return input;
            }
            return Arrays.stream(input.split(" "))
                    .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                    .collect(Collectors.joining(" "));
        }


    public EnderecoResponseDTO EnderecoToResponseDTO(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro().getNomeBairro(),
                endereco.getBairro().getCidade().getNomeCidade(),
                endereco.getBairro().getCidade().getEstado().getNomeEstado(),
                endereco.getBairro().getCidade().getEstado().getRegiao().getNomeRegiao(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }

    public EnderecoResponse EnderecoToResponse(Endereco endereco, Link link) {
        return new EnderecoResponse(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro().getNomeBairro(),
                endereco.getBairro().getCidade().getNomeCidade(),
                endereco.getBairro().getCidade().getEstado().getNomeEstado(),
                endereco.getBairro().getCidade().getEstado().getRegiao().getNomeRegiao(),
                endereco.getComplemento(),
                endereco.getNumero(),
                link
        );
    }


    public Endereco createEnderecoUsingProcedure(EnderecoRequest enderecoRequest, Future<ViaCepResponse> futureViaCepResponse) {
        // Chama o método RequestToEndereco para processar os dados e criar o endereço
        Endereco endereco = RequestToEndereco(enderecoRequest, futureViaCepResponse);

        // Configurar e executar a stored procedure
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("insert_endereco");

        // Registrar os parâmetros de entrada
        storedProcedure.registerStoredProcedureParameter("p_cep", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_numero", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_complemento", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_logradouro", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_nome_bairro", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_nome_cidade", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_nome_estado", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_nome_regiao", String.class, ParameterMode.IN);
        // Registrar o parâmetro de saída
        storedProcedure.registerStoredProcedureParameter("p_id_endereco", Long.class, ParameterMode.OUT);

        // Definir os valores dos parâmetros de entrada
        storedProcedure.setParameter("p_cep", endereco.getCep());
        storedProcedure.setParameter("p_numero", endereco.getNumero());
        storedProcedure.setParameter("p_complemento", endereco.getComplemento());
        storedProcedure.setParameter("p_logradouro", endereco.getLogradouro());
        storedProcedure.setParameter("p_nome_bairro", endereco.getBairro().getNomeBairro());
        storedProcedure.setParameter("p_nome_cidade", endereco.getBairro().getCidade().getNomeCidade());
        storedProcedure.setParameter("p_nome_estado", endereco.getBairro().getCidade().getEstado().getNomeEstado());
        storedProcedure.setParameter("p_nome_regiao", endereco.getBairro().getCidade().getEstado().getRegiao().getNomeRegiao());

        // Executar a stored procedure
        storedProcedure.execute();

        // Obter o valor do parâmetro de saída
        Long idEndereco = (Long) storedProcedure.getOutputParameterValue("p_id_endereco");

        // Atribuir o ID gerado no objeto Endereco
        endereco.setId(idEndereco);

        return endereco;
    }




    public Endereco updateEnderecoUsingProcedure(Long idEndereco, EnderecoRequest enderecoRequest, Future<ViaCepResponse> futureViaCepResponse) {
        // Chama o método RequestToEndereco para processar os dados e preparar o endereço para atualização
        Endereco endereco = RequestToEndereco(enderecoRequest, futureViaCepResponse);

        // A partir daqui, cria os registros nas tabelas correspondentes, verificando a existência
        Regiao regiao = regiaoRepository.findByNomeRegiao(endereco.getBairro().getCidade().getEstado().getRegiao().getNomeRegiao())
                .orElseGet(() -> regiaoRepository.save(new Regiao(endereco.getBairro().getCidade().getEstado().getRegiao().getNomeRegiao())));

        Estado estado = estadoRepository.findByNomeEstadoAndRegiao(endereco.getBairro().getCidade().getEstado().getNomeEstado(), regiao)
                .orElseGet(() -> {
                    Estado novoEstado = new Estado(endereco.getBairro().getCidade().getEstado().getNomeEstado());
                    novoEstado.setRegiao(regiao);
                    return estadoRepository.save(novoEstado);
                });

        Cidade cidade = cidadeRepository.findByNomeCidadeAndEstado(endereco.getBairro().getCidade().getNomeCidade(), estado)
                .orElseGet(() -> {
                    Cidade novaCidade = new Cidade(endereco.getBairro().getCidade().getNomeCidade());
                    novaCidade.setEstado(estado);
                    return cidadeRepository.save(novaCidade);
                });

        String nomeBairro = capitalizeWords(endereco.getBairro().getNomeBairro());
        Bairro bairro = bairroRepository.findByNomeBairroAndCidade(nomeBairro, cidade)
                .orElseGet(() -> {
                    Bairro novoBairro = new Bairro(nomeBairro);
                    novoBairro.setCidade(cidade);
                    return bairroRepository.save(novoBairro);
                });


        endereco.setBairro(bairro);


        // Chama a procedure para atualizar o endereço no banco
        enderecoRepository.updateEnderecoWithProcedure(
                idEndereco,
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getLogradouro(),
                endereco.getBairro().getNomeBairro(),
                endereco.getBairro().getCidade().getNomeCidade(),
                endereco.getBairro().getCidade().getEstado().getNomeEstado(),
                endereco.getBairro().getCidade().getEstado().getRegiao().getNomeRegiao()
        );

        return endereco;
    }


    public void deleteEnderecoUsingProcedure(Long idEndereco) {
        // Check if any user is linked to this address
        boolean isLinked = usuarioRepository.existsByEnderecoId(idEndereco);
        if (isLinked) {
            throw new EnderecoVinculadoException("Não é possível apagar um endereço vinculado a um usuário.");
        }

        try {
            enderecoRepository.deleteEnderecoById(idEndereco);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar endereço: " + e.getMessage(), e);
        }
    }


}

package com.fiap.FraudWatch.service;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponse;
import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.model.*;
import com.fiap.FraudWatch.repository.BairroRepository;
import com.fiap.FraudWatch.repository.CidadeRepository;
import com.fiap.FraudWatch.repository.EstadoRepository;
import com.fiap.FraudWatch.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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

    public EnderecoService(BairroRepository bairroRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository, RegiaoRepository regiaoRepository) {
        this.bairroRepository = bairroRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.regiaoRepository = regiaoRepository;
    }




    public Endereco requestToEndereco(EnderecoRequest enderecoRequest,  Future<ViaCepResponse> futureViaCepResponse) {
        ViaCepResponse viaCepResponse;
        try {
            viaCepResponse = futureViaCepResponse.get(); // This will block until the task is complete
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to get ViaCepResponse", e);
        }

        // Buscar ou criar regiao
        Regiao regiaoEntity = regiaoRepository.findByNomeRegiao(futureViaCepResponse.resultNow().regiao())
                .orElseGet(() -> {
                    Regiao novaRegiao = new Regiao(futureViaCepResponse.resultNow().regiao() != null ? futureViaCepResponse.resultNow().regiao() : capitalizeWords(enderecoRequest.regiao()));
                    return regiaoRepository.save(novaRegiao); // Salva a nova Região
                });

        // Buscar ou criar estado
        Estado estadoEntity = estadoRepository.findByNomeEstado(futureViaCepResponse.resultNow().estado())
                .orElseGet(() -> {
                    Estado novoEstado = new Estado(futureViaCepResponse.resultNow().estado() != null ? futureViaCepResponse.resultNow().estado() : capitalizeWords(enderecoRequest.estado()));
                    novoEstado.setRegiao(regiaoEntity);
                    return estadoRepository.save(novoEstado); // Salva o novo Estado
                });

        // Buscar ou criar cidade
        Cidade cidadeEntity = cidadeRepository.findByNomeCidade(futureViaCepResponse.resultNow().localidade())
                .orElseGet(() -> {
                    Cidade novaCidade = new Cidade(futureViaCepResponse.resultNow().localidade() != null
                            ? futureViaCepResponse.resultNow().localidade() : capitalizeWords(enderecoRequest.cidade()));
                    novaCidade.setEstado(estadoEntity);
                    return cidadeRepository.save(novaCidade); // Salva a nova Cidade
                });



        // Buscar ou criar bairro
        String nomeBairroParaBusca = (futureViaCepResponse.resultNow().bairro() != null) ? futureViaCepResponse.resultNow().bairro() : enderecoRequest.bairro();
        Bairro bairroEntity = bairroRepository.findByNomeBairro(nomeBairroParaBusca)
                .orElseGet(() -> {
                    String nomeBairro = (futureViaCepResponse.resultNow().bairro() != null && !futureViaCepResponse.resultNow().bairro().isEmpty())
                            ? futureViaCepResponse.resultNow().bairro()
                            : (enderecoRequest.bairro() != null && !enderecoRequest.bairro().isEmpty())
                            ? capitalizeWords(enderecoRequest.bairro())
                            : "Bairro Padrão"; // Defina um valor padrão ou tratamento para null
                    Bairro novoBairro = new Bairro(nomeBairro);
                    novoBairro.setCidade(cidadeEntity);
                    return bairroRepository.save(novoBairro); // Salva o novo Bairro no banco de dados
                });


        // Formatar para primeira letra maiúscula
        String logradouro = (futureViaCepResponse.resultNow().logradouro() != null && !futureViaCepResponse.resultNow().logradouro().isEmpty())
                ? capitalizeWords(futureViaCepResponse.resultNow().logradouro())
                : (enderecoRequest.logradouro() != null && !enderecoRequest.logradouro().isEmpty())
                ? capitalizeWords(enderecoRequest.logradouro())
                : "Logradouro Padrão"; // Defina um valor padrão ou tratamento para null













        // Atualizar a cidade com o estado e a região
        bairroEntity.setCidade(cidadeEntity);
        cidadeEntity.setEstado(estadoEntity);
        estadoEntity.setRegiao(regiaoEntity);

        // Criar o objeto Endereco
        Endereco endereco = new Endereco();
        endereco.setCep(futureViaCepResponse.resultNow().cep());
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


    public EnderecoResponse enderecoToResponse(Endereco endereco) {
        return new EnderecoResponse(
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
}

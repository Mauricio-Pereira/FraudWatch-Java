package com.fiap.FraudWatch.dto.accountDto;
import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.service.EnderecoServiceAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @Autowired
    private EnderecoServiceAsync enderecoServiceAsync;

    @GetMapping
    public ViaCepResponse getCep(@RequestParam("cep") String cep) {
        // Bloqueia até obter a resposta (pode ser melhorado com programação reativa)
        return enderecoServiceAsync.ObterEnderecoPorCep(cep);
    }
}

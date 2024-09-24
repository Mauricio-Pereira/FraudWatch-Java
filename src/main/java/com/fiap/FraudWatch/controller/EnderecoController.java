package com.fiap.FraudWatch.controller;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponse;
import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.model.Endereco;
import com.fiap.FraudWatch.repository.EnderecoRepository;
import com.fiap.FraudWatch.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereco", description = "API de Endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoRepository enderecoRepository;
    private Pageable pageable = PageRequest.of(0,4, Sort.by("cep").ascending());

    @Operation(summary = "Criar um endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    public ResponseEntity<EnderecoResponse> CreateEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest){
        ViaCepResponse viaCepResponse = enderecoService.obterEnderecoPorCep(enderecoRequest.cep());
        Endereco enderecoConvertido = enderecoService.requestToEndereco(enderecoRequest, viaCepResponse);
        Endereco enderecoCriado = enderecoRepository.save(enderecoConvertido);
        EnderecoResponse enderecoResponse = enderecoService.enderecoToResponse(enderecoCriado);
        return new ResponseEntity<>(enderecoResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Criar varios enderecos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enderecos criados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/list")
    public ResponseEntity<List<EnderecoResponse>> CreateEnderecos(@Valid @RequestBody List<EnderecoRequest> enderecoRequestList){
        List<EnderecoResponse> enderecoResponseList = new ArrayList<>();
        for (EnderecoRequest enderecoRequest : enderecoRequestList){
            ViaCepResponse viaCepResponse = enderecoService.obterEnderecoPorCep(enderecoRequest.cep());
            Endereco enderecoConvertido = enderecoService.requestToEndereco(enderecoRequest, viaCepResponse);
            Endereco enderecoCriado = enderecoRepository.save(enderecoConvertido);
            enderecoResponseList.add(enderecoService.enderecoToResponse(enderecoCriado));
        }
        return new ResponseEntity<>(enderecoResponseList, HttpStatus.CREATED);
    }


    @Operation(summary = "Mostrar todos os enderecos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enderecos listados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema()))
    })
    @GetMapping("/page/{page}")
    public ResponseEntity<List<EnderecoResponse>> ReadAllEnderecos(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 4, Sort.by("id").ascending());
        Page<Endereco> enderecoPage = enderecoRepository.findAll(pageable);
        if (enderecoPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EnderecoResponse> enderecoResponseList = new ArrayList<>();
        for (Endereco endereco : enderecoPage){
            enderecoResponseList.add(enderecoService.enderecoToResponse(endereco));
        }
        return new ResponseEntity<>(enderecoResponseList, HttpStatus.OK);
    }


    @Operation(summary = "Mostrar endereco por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado", content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> ReadEnderecoById(@PathVariable Long id){
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EnderecoResponse enderecoResponse = enderecoService.enderecoToResponse(enderecoSalvo.get());
        return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar endereco por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado", content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponse> UpdateEnderecoById(@Valid @PathVariable Long id, @RequestBody EnderecoRequest enderecoRequest){
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ViaCepResponse viaCepResponse = enderecoService.obterEnderecoPorCep(enderecoRequest.cep());
        Endereco enderecoConvertido = enderecoService.requestToEndereco(enderecoRequest, viaCepResponse);
        enderecoConvertido.setId(enderecoSalvo.get().getId());
        Endereco enderecoAtualizado = enderecoRepository.save(enderecoConvertido);
        EnderecoResponse enderecoResponse = enderecoService.enderecoToResponse(enderecoAtualizado);
        return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
    }


    @Operation(summary = "Deletar endereco por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResponse> DeleteEndereco(@PathVariable Long id){
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        enderecoRepository.delete(enderecoSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

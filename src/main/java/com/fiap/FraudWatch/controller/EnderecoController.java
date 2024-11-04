package com.fiap.FraudWatch.controller;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponse;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponseDTO;
import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.model.Endereco;
import com.fiap.FraudWatch.repository.EnderecoRepository;
import com.fiap.FraudWatch.service.EnderecoService;
import com.fiap.FraudWatch.service.EnderecoServiceAsync;
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

import java.util.*;
import java.util.concurrent.Future;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereco", description = "API de Endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecoServiceAsync enderecoServiceAsync;
    private Pageable pageable = PageRequest.of(0,4, Sort.by("cep").ascending());



    @Operation(summary = "Criar um endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> CreateEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest){
        Future<ViaCepResponse> viaCepResponse = enderecoServiceAsync.ObterEnderecoPorCepAsync(enderecoRequest.cep());
        Endereco enderecoConvertido = enderecoService.RequestToEndereco(enderecoRequest, viaCepResponse);
        Endereco enderecoCriado = enderecoRepository.save(enderecoConvertido);
        EnderecoResponseDTO enderecoResponse = enderecoService.EnderecoToResponseDTO(enderecoCriado);
        return new ResponseEntity<>(enderecoResponse, HttpStatus.CREATED);
    }


    @Operation(summary = "Criar varios enderecos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enderecos criados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/list")
    public ResponseEntity<Map<String, Object>> CreateEnderecos(@Valid @RequestBody List<EnderecoRequest> enderecoRequestList) {
        List<EnderecoResponseDTO> enderecoResponseList = new ArrayList<>();
        int successCount = 0;
        int failureCount = 0;

        for (EnderecoRequest enderecoRequest : enderecoRequestList) {
            try {
                Future<ViaCepResponse> viaCepResponse = enderecoServiceAsync.ObterEnderecoPorCepAsync(enderecoRequest.cep());
                Endereco enderecoConvertido = enderecoService.RequestToEndereco(enderecoRequest, viaCepResponse);
                Endereco enderecoCriado = enderecoRepository.save(enderecoConvertido);
                enderecoResponseList.add(enderecoService.EnderecoToResponseDTO(enderecoCriado));
                successCount++;
            } catch (Exception e) {
                failureCount++;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("successCount", successCount);
        result.put("failureCount", failureCount);
        result.put("createdEnderecos", enderecoResponseList);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
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
            EnderecoResponseDTO enderecoResponseDTO = enderecoService.EnderecoToResponseDTO(endereco);
            EnderecoResponse enderecoResponse = enderecoService.EnderecoToResponse(endereco,
                    linkTo(methodOn(EnderecoController.class)
                            .ReadEnderecoById(enderecoResponseDTO.id())
                    ).withSelfRel());
            enderecoResponseList.add(enderecoResponse);
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
        EnderecoResponse enderecoResponse = enderecoService.EnderecoToResponse(enderecoSalvo.get(),
                linkTo(methodOn(EnderecoController.class)
                        .ReadEnderecoById(id)
                ).withSelfRel());
        return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
    }

    @Operation(summary = "Atualizar endereco por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado", content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> UpdateEnderecoById(@Valid @PathVariable Long id, @RequestBody EnderecoRequest enderecoRequest){
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Future<ViaCepResponse> viaCepResponse = enderecoServiceAsync.ObterEnderecoPorCepAsync(enderecoRequest.cep());
        Endereco enderecoConvertido = enderecoService.RequestToEndereco(enderecoRequest, viaCepResponse);
        enderecoConvertido.setId(enderecoSalvo.get().getId());
        Endereco enderecoAtualizado = enderecoRepository.save(enderecoConvertido);
        EnderecoResponseDTO enderecoResponse = enderecoService.EnderecoToResponseDTO(enderecoAtualizado);
        return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
    }


    @Operation(summary = "Deletar endereco por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado", content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> DeleteEndereco(@PathVariable Long id){
        Optional<Endereco> enderecoSalvo = enderecoRepository.findById(id);
        if (enderecoSalvo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        enderecoRepository.delete(enderecoSalvo.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Operation(summary = "Criar um endereco usando procedure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereco criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/procedure")
    public ResponseEntity<EnderecoResponseDTO> createEndereco(@Valid @RequestBody EnderecoRequest enderecoRequest) {
        try {
            // Chamada assíncrona para obter dados do ViaCep
            Future<ViaCepResponse> viaCepResponseFuture = enderecoServiceAsync.ObterEnderecoPorCepAsync(enderecoRequest.cep());

            // Delegar a lógica de criação para o EnderecoService
            Endereco enderecoCriado = enderecoService.createEnderecoUsingProcedure(enderecoRequest, viaCepResponseFuture);

            // Converte Endereco para EnderecoResponseDTO para a resposta
            EnderecoResponseDTO enderecoResponse = enderecoService.EnderecoToResponseDTO(enderecoCriado);

            return new ResponseEntity<>(enderecoResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Atualizar um endereco usando procedure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PutMapping("/procedure/{id}")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        try {
            // Chamada assíncrona para obter dados do ViaCep
            Future<ViaCepResponse> viaCepResponseFuture = enderecoServiceAsync.ObterEnderecoPorCepAsync(enderecoRequest.cep());

            // Delegar a lógica de atualização para o EnderecoService
            Endereco enderecoAtualizado = enderecoService.updateEnderecoUsingProcedure(id, enderecoRequest, viaCepResponseFuture);

            // Converte Endereco para EnderecoResponseDTO para a resposta
            EnderecoResponseDTO enderecoResponse = enderecoService.EnderecoToResponseDTO(enderecoAtualizado);

            return new ResponseEntity<>(enderecoResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/procedure/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteEnderecoUsingProcedure(id);
        return ResponseEntity.ok().build(); // Retorna 204 No Content se a exclusão for bem-sucedida
    }





}

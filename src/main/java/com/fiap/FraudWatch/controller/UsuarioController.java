package com.fiap.FraudWatch.controller;

import com.fiap.FraudWatch.dto.usuarioDto.UsuarioRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioResponse;
import com.fiap.FraudWatch.model.Usuario;
import com.fiap.FraudWatch.repository.UsuarioRepository;
import com.fiap.FraudWatch.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "API de Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Pageable pageable = PageRequest.of(0,4, Sort.by("nome").ascending());

    @Operation(summary = "Criar um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> CreateUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        Usuario usuarioConvertido = usuarioService.requestToUsuario(usuarioRequest, usuarioRequest.endereco());
        Usuario usuarioCriado = usuarioRepository.save(usuarioConvertido);
        UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioCriado);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Criar varios usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuarios criados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/list")
    public ResponseEntity<List<UsuarioResponse>> CreateUsuarios(@Valid @RequestBody List<UsuarioRequest> usuarioRequestList){
        List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
        int successCount = 0;
        int failureCount = 0;
        try {
            for (UsuarioRequest usuarioRequest : usuarioRequestList) {
                Usuario usuarioConvertido = usuarioService.requestToUsuario(usuarioRequest, usuarioRequest.endereco());
                Usuario usuarioCriado = usuarioRepository.save(usuarioConvertido);
                UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioCriado);
                usuarioResponseList.add(usuarioResponse);
                successCount++;
            }
        } catch (Exception e) {
            failureCount++;
        }
        Map<String, Object> response = Map.of(
                "successCount", successCount,
                "failureCount", failureCount,
                "createdUsuarios", usuarioResponseList
        );
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Buscar todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios encontrados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @GetMapping("/page/{page}")
    public ResponseEntity<List<UsuarioResponse>> GetAllUsuarios(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 4, Sort.by("id").ascending());
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        if (usuarioPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
        for (Usuario usuario : usuarioPage) {
            usuarioResponseList.add(usuarioService.usuarioToResponse(usuario));
        }
        return new ResponseEntity<>(usuarioResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Buscar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> GetUsuarioById(@PathVariable Long id){
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioSalvo.get());
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }


    @Operation(summary = "Atualizar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> UpdateUsuarioById(@PathVariable Long id, @Valid @RequestBody UsuarioRequest usuarioRequest){
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Usuario usuarioConvertido = usuarioService.updateUsuario(id,usuarioRequest, usuarioRequest.endereco());
        usuarioConvertido.setId(id);
        Usuario usuarioAtualizado = usuarioRepository.save(usuarioConvertido);
        UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioAtualizado);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @Operation(summary = "Deletar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> DeleteUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

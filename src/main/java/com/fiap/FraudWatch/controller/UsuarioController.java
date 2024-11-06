package com.fiap.FraudWatch.controller;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioLoginRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioRequest;
import com.fiap.FraudWatch.dto.usuarioDto.UsuarioResponseDTO;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "API de Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private Pageable pageable = PageRequest.of(0,400, Sort.by("nome").ascending());

    @Operation(summary = "Criar um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> CreateUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        Usuario usuarioConvertido = usuarioService.RequestToUsuario(usuarioRequest, usuarioRequest.endereco());
        Usuario usuarioCriado = usuarioRepository.save(usuarioConvertido);
        UsuarioResponseDTO usuarioResponse = usuarioService.usuarioToResponse(usuarioCriado);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Criar varios usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuarios criados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/list")
    public ResponseEntity<List<UsuarioResponseDTO>> CreateUsuarios(@Valid @RequestBody List<UsuarioRequest> usuarioRequestList){
        List<UsuarioResponseDTO> usuarioResponseList = new ArrayList<>();
        int successCount = 0;
        int failureCount = 0;
        try {
            for (UsuarioRequest usuarioRequest : usuarioRequestList) {
                Usuario usuarioConvertido = usuarioService.RequestToUsuario(usuarioRequest, usuarioRequest.endereco());
                Usuario usuarioCriado = usuarioRepository.save(usuarioConvertido);
                UsuarioResponseDTO usuarioResponse = usuarioService.usuarioToResponse(usuarioCriado);
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
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        if (usuarioPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
        for (Usuario usuario : usuarioPage) {
            UsuarioResponseDTO usuarioResponseDTO = usuarioService.usuarioToResponse(usuario);
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuario, linkTo(
                    methodOn(UsuarioController.class)
                            .GetUsuarioById(usuarioResponseDTO.id())
            ).withSelfRel()
            );
            usuarioResponseList.add(usuarioResponse);

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
        UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioSalvo.get(), linkTo(
                        methodOn(UsuarioController.class)
                                .GetUsuarioById(id)
                ).withSelfRel()
        );
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }


    @Operation(summary = "Atualizar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> UpdateUsuarioById(@PathVariable Long id, @Valid @RequestBody UsuarioRequest usuarioRequest){
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Usuario usuarioConvertido = usuarioService.UpdateUsuario(id,usuarioRequest, usuarioRequest.endereco());
        usuarioConvertido.setId(id);
        Usuario usuarioAtualizado = usuarioRepository.save(usuarioConvertido);
        UsuarioResponseDTO usuarioResponse = usuarioService.usuarioToResponse(usuarioAtualizado);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }

    @Operation(summary = "Deletar usuario por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> DeleteUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
        if (usuarioSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Fazer login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login feito com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> Login(@Valid @RequestBody
                                                    UsuarioLoginRequest usuarioLoginRequest){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLoginRequest.email());
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!usuario.get().getSenha().equals(usuarioLoginRequest.senha())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UsuarioResponseDTO usuarioResponse = usuarioService.usuarioToResponse(usuario.get());
        return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
    }


    @Operation(summary = "Criar um usuario com procedure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PostMapping("/procedure")
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            // Chama o serviço para criar o usuário e o endereço
            Usuario usuario = usuarioService.createUsuarioWithProcedure(usuarioRequest);

            // Converte o usuário para o DTO de resposta
            UsuarioResponseDTO usuarioResponse = usuarioService.usuarioToResponse(usuario);

            return ResponseEntity.status(201).body(usuarioResponse); // Retorna 201 Created
        } catch (RuntimeException e) {
            // Retorna uma resposta com erro, se algo falhar
            return ResponseEntity.badRequest().body(null); // Aqui você pode personalizar a resposta de erro
        }
    }

    @Operation(summary = "Atualizar um usuario com procedure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @PutMapping("/procedure/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
        try {
            // Chama o serviço para atualizar o usuário e o endereço
            Usuario usuario = usuarioService.updateUsuarioWithProcedure(id, usuarioRequest);

            // Converte o usuário para o DTO de resposta
            UsuarioResponseDTO usuarioResponse = usuarioService.usuarioToResponse(usuario);

            return ResponseEntity.ok(usuarioResponse); // Retorna 200 OK
        } catch (RuntimeException e) {
            // Retorna uma resposta com erro, se algo falhar
            return ResponseEntity.badRequest().body(null); // Aqui você pode personalizar a resposta de erro
        }
    }

    @Operation(summary = "Deletar um usuario com procedure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    @DeleteMapping("/procedure/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            // Chama o serviço para deletar o usuário e o endereço
            usuarioService.deleteUsuarioWithProcedure(id);

            return ResponseEntity.ok().build(); // Retorna 200 OK
        } catch (RuntimeException e) {
            // Retorna uma resposta com erro, se algo falhar
            return ResponseEntity.badRequest().build(); // Aqui você pode personalizar a resposta de erro
        }
    }
}

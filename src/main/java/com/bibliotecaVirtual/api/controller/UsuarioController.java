package com.bibliotecaVirtual.api.controller;

import com.bibliotecaVirtual.api.dto.request.UsuarioRequestDTO;
import com.bibliotecaVirtual.api.dto.response.LivroResponseDTO;
import com.bibliotecaVirtual.api.dto.response.UsuarioResponse;
import com.bibliotecaVirtual.api.exception.UsuarioJaCadastradoException;
import com.bibliotecaVirtual.api.exception.UsuarioNotFoundException;
import com.bibliotecaVirtual.api.repository.UsuarioRepository;
import com.bibliotecaVirtual.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Usuario já cadastrado no banco de dados")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody UsuarioRequestDTO requestDTO) throws UsuarioJaCadastradoException {

        UsuarioResponse response = usuarioService.criar(requestDTO);

        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todos os usuarios"),
            @ApiResponse(responseCode = "400", description = "UsuarioId não encontrado")
    })
    @GetMapping
    public ResponseEntity<?> obterUsuario(@RequestParam(value = "id", required = false) Long id) throws UsuarioNotFoundException {

        return ResponseEntity.ok(
                id != null ? usuarioService.obterUsuarioPorId(id) : usuarioService.obterUsuario()
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza um determinado usuario"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar usuario no banco de dados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable("id") Long id, @RequestBody UsuarioRequestDTO requestDTO) throws UsuarioNotFoundException {
        UsuarioResponse responseDTO = usuarioService.atualizar(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Usuario não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> apagar(@PathVariable("id") Long id) throws UsuarioNotFoundException {
        this.usuarioService.apagar(id);
        return ResponseEntity.noContent().build();
    }

}

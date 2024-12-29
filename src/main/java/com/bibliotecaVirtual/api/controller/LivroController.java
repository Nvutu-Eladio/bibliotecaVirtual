package com.bibliotecaVirtual.api.controller;

import com.bibliotecaVirtual.api.dto.request.LivroRequestDTO;
import com.bibliotecaVirtual.api.dto.response.LivroResponseDTO;
import com.bibliotecaVirtual.api.repository.LivroRepository;
import com.bibliotecaVirtual.api.service.LivroService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Livro já cadastrado no banco de dados")
    })
    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody LivroRequestDTO requestDTO) {

        LivroResponseDTO response = livroService.criar(requestDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getLivroId())
                .toUri()).body(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todos os livros"),
    })
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> obterLivro() {
        return ResponseEntity.ok(livroService.obterLivro());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza um determinado livro"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar livro no banco de dados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable("id") String id, @RequestBody LivroRequestDTO requestDTO) {
        LivroResponseDTO responseDTO = livroService.atualizar(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> apagar(@PathVariable("id") String id) {
        this.livroService.apagar(id);
        return ResponseEntity.noContent().build();
    }


}

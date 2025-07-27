package com.bibliotecaVirtual.api.controller;

import com.bibliotecaVirtual.api.dto.request.LivroRequestDTO;
import com.bibliotecaVirtual.api.dto.response.LivroResponseDTO;
import com.bibliotecaVirtual.api.service.LivroService;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
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
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private MeterRegistry registry;

    Logger logger = org.slf4j.LoggerFactory.getLogger(LivroController.class);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Livro já cadastrado no banco de dados")
    })
    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody LivroRequestDTO livroRequestDTO) {

        LivroResponseDTO response = livroService.criar(livroRequestDTO);

        logger.info("Livro criado com sucesso: {}", response.getTitulo());
        registry.counter("livro.criado", "titulo", response.getTitulo()).increment();

        return ResponseEntity.ok(response);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todos os livros"),
            @ApiResponse(responseCode = "400", description = "Livro com id não encontrado")
    })
    @GetMapping
    public ResponseEntity<?> obterLivro(@RequestParam(value = "id", required = false) Long id) {

        return ResponseEntity.ok(
                id != null ? livroService.obterLivroPorId(id) : livroService.obterLivro()
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todos os livros por id"),
            @ApiResponse(responseCode = "400", description = "Livro com id não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> obterLivroPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(livroService.obterLivroPorId(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza um determinado livro"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar livro no banco de dados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable("id") Long id, @RequestBody LivroRequestDTO requestDTO) {
        LivroResponseDTO responseDTO = livroService.atualizar(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> apagar(@PathVariable("id") Long id) {
        this.livroService.apagar(id);
        return ResponseEntity.noContent().build();
    }


}

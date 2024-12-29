package com.bibliotecaVirtual.api.controller;

import com.bibliotecaVirtual.api.dto.request.LivroRequestDTO;
import com.bibliotecaVirtual.api.dto.response.LivroResponseDTO;
import com.bibliotecaVirtual.api.model.Livro;
import com.bibliotecaVirtual.api.repository.LivroRepository;
import com.bibliotecaVirtual.api.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody LivroRequestDTO requestDTO) {
        LivroResponseDTO response = livroService.criar(requestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getLivroId())
                .toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> obterProposta() {
        return ResponseEntity.ok(livroService.obterLivro());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable("id") String id, @RequestBody LivroRequestDTO requestDTO){
        LivroResponseDTO responseDTO = livroService.atualizar(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> apagar(@PathVariable("id") String id) {
        this.livroService.apagar(id);
        return ResponseEntity.noContent().build();
    }



}

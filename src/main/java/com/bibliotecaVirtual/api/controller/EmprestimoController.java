package com.bibliotecaVirtual.api.controller;

import com.bibliotecaVirtual.api.dto.request.EmprestimoRequestDTO;
import com.bibliotecaVirtual.api.dto.response.EmprestimoResponseDTO;
import com.bibliotecaVirtual.api.exception.EmprestimoNotFoundException;
import com.bibliotecaVirtual.api.exception.LivroIndisponivelException;
import com.bibliotecaVirtual.api.service.EmprestimoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "O livro não está disponível para empréstimo"),
    })
    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> criar(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) throws LivroIndisponivelException {

        EmprestimoResponseDTO response = emprestimoService.criar(emprestimoRequestDTO);

        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista todos os emprestimos por id"),
            @ApiResponse(responseCode = "400", description = "Livro com id não encontrado")
    })
    @GetMapping
    public ResponseEntity<?> obterEmprestimo(@RequestParam(value = "id", required = false) Long id) throws EmprestimoNotFoundException {

        return ResponseEntity.ok(
          id != null ? emprestimoService.obterEmprestimoPorId(id) : emprestimoService.obterEmprestimo()
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Emprestimo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> apagar(@PathVariable("id") Long id) throws EmprestimoNotFoundException {
        this.emprestimoService.apagar(id);
        return ResponseEntity.noContent().build();
    }

}

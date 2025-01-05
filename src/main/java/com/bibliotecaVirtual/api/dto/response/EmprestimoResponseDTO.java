package com.bibliotecaVirtual.api.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmprestimoResponseDTO {

    private Long emprestimoId;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucaoEmprestimo;

    private String status;

    private Long usuarioId;

    private String nome;

    private Long livroId;

    private String titulo;


}

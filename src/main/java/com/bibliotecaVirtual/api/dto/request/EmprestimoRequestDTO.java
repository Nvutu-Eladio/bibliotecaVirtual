package com.bibliotecaVirtual.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmprestimoRequestDTO {

    private Long livroId;

    private Long usuarioId;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucaoEmprestimo;

    private String status;
}

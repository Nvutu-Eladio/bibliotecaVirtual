package com.bibliotecaVirtual.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmprestimoDTO {

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucaoEmprestimo;

    private String status;
}

package com.bibliotecaVirtual.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private String titulo;

    private String autor;

    private String genero;

    private Boolean disponivel;
}

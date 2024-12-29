package com.bibliotecaVirtual.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    private String titulo;

    private String autor;

    private String genero;

    private Boolean disponivel;
}

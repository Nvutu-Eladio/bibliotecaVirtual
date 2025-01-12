package com.bibliotecaVirtual.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroResponseDTO {

    @Getter
    private Long livroId;

    private String titulo;

    private String autor;

    private String genero;

    private Boolean disponivel;
}

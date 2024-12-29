package com.bibliotecaVirtual.api.dto.response;

import com.bibliotecaVirtual.api.model.Emprestimo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

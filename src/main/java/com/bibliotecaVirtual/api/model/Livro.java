package com.bibliotecaVirtual.api.model;

import com.bibliotecaVirtual.api.dto.LivroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *
 * A entidade Livro está relacionada com a entidade Emprestimo por meio de um relacionamento
 * um-para-muitos (um livro pode ter vários empréstimos).
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long livroId;

    private String titulo;

    private String autor;

    private String genero;

    private Boolean disponivel;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<Emprestimo> Emprestimos;
}

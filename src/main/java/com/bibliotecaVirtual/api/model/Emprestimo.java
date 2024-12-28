package com.bibliotecaVirtual.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * A entidade Emprestimo estabelece dois relacionamentos muitos-para-um:
 * Com Livro (vários empréstimos podem referenciar o mesmo livro).
 * Com Usuario (vários empréstimos podem ser feitos por um mesmo usuário).
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emprestimoId;

    @ManyToOne
    @JoinColumn(name = "livroId", nullable = false)
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucaoEmprestimo;

    private String status;
}

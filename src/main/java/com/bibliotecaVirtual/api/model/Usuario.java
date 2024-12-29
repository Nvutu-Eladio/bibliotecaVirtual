package com.bibliotecaVirtual.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A entidade Usuario também está relacionada com a entidade Emprestimo por meio de um relacionamento
 * um-para-muitos (um usuário pode realizar vários empréstimos).
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(unique = true)
    private String nome;

    private String email;

    private String estado;

    private String cidade;

    private String bairro;

    private String rua;

    private String cep;

    private String numero;

    private String complemento;

    private String tipo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Emprestimo> Emprestimos;
}

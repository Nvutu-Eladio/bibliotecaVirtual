package com.bibliotecaVirtual.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private Long usuarioId;

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

}

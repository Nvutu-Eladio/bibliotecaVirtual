package com.bibliotecaVirtual.api.client.viacep;

public record Endereco (
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
){}

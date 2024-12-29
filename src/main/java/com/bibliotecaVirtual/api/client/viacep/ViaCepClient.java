package com.bibliotecaVirtual.api.client.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepClient {

    @GetMapping("{cep}/json/")
    Endereco getEndereco(@PathVariable String cep);
}

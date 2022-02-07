package br.com.teste.cd2.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.teste.cd2.model.Endereco;

@FeignClient(name = "ViaCep", url = "http://viacep.com.br/ws/")
public interface ViaCepService {

    @GetMapping("{cep}/json")
    Endereco buscarCep(@PathVariable String cep);
}

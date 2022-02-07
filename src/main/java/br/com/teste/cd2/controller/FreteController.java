package br.com.teste.cd2.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.cd2.model.Frete;
import br.com.teste.cd2.model.dto.FreteDto;
import br.com.teste.cd2.model.form.FreteCreateForm;
import br.com.teste.cd2.service.FreteService;

@RestController
@RequestMapping("/cep")
public class FreteController {

    @Autowired
    private FreteService freteService;

    @PostMapping()
    public ResponseEntity<?> calcularFrete(@RequestBody FreteCreateForm freteCreate) throws ParseException {
        try {
            Frete frete = freteService.calcularFrete(freteCreate);
            FreteDto freteDto = new FreteDto(frete);
            return ResponseEntity.ok(freteDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}

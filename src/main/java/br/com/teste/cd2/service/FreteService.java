package br.com.teste.cd2.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.cd2.model.Endereco;
import br.com.teste.cd2.model.Frete;
import br.com.teste.cd2.model.form.FreteCreateForm;
import br.com.teste.cd2.repository.FreteRepository;

@Service
public class FreteService {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private FreteRepository freteRepository;

    public Frete calcularFrete(FreteCreateForm freteCreate) throws ParseException {

        if (freteCreate.getCepDestino().isEmpty() && freteCreate.getCepOrigem().isEmpty()) {
            throw new IllegalArgumentException("Cep de origem e destino não podem ser vazios");
        }
        try {
            Endereco enderecoDestino = viaCepService.buscarCep(freteCreate.getCepDestino());
            Endereco enderecoOrigem = viaCepService.buscarCep(freteCreate.getCepOrigem());

            double precoPeso = calcularPrecoPeso(freteCreate.getPeso());
            double desconto;

            Frete calculoFrete = new Frete();

            calculoFrete.setCepDestino(enderecoDestino.getCep());
            calculoFrete.setCepOrigem(enderecoOrigem.getCep());
            calculoFrete.setPeso(freteCreate.getPeso());
            calculoFrete.setNomeDestinatario(freteCreate.getNome());
            calculoFrete.setDataConsulta(new Date());

            if (enderecoDestino.getDdd().equals(enderecoOrigem.getDdd())) {

                desconto = 50.0 / 100;
                calculoFrete.setTotalFrete(precoPeso - (desconto * precoPeso));
                calculoFrete.setDataPrevistaEntrega(previsaoDeEntrega(1));

                freteRepository.save(calculoFrete);

                return calculoFrete;
            }

            if (enderecoDestino.getUf().equals(enderecoOrigem.getUf())) {
                desconto = 75.0 / 100;
                calculoFrete.setTotalFrete(precoPeso - (desconto * precoPeso));
                calculoFrete.setDataPrevistaEntrega(previsaoDeEntrega(3));

                freteRepository.save(calculoFrete);

                return calculoFrete;
            }

            calculoFrete.setDataPrevistaEntrega(previsaoDeEntrega(10));
            calculoFrete.setTotalFrete(precoPeso);
            freteRepository.save(calculoFrete);

            return calculoFrete;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cep não encontrado");
        }

    }

    private Double calcularPrecoPeso(Double peso) {
        Double valorArredondado = Math.floor(peso);
        return valorArredondado;
    }

    private String previsaoDeEntrega(Integer dias) throws ParseException {
        Date dataAtual = new Date();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dataAtual);
        calendar.add(Calendar.DATE, dias);
        dataAtual = calendar.getTime();
        return dataFormatada.format(dataAtual);
    }

}

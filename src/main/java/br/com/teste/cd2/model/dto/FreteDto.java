package br.com.teste.cd2.model.dto;

import br.com.teste.cd2.model.Frete;

public class FreteDto {

    private String cepOrigem;
    private String cepDestino;
    private Double totalFrete;
    private String dataPrevistaEntrega;

    public FreteDto(Frete frete) {
        this.cepOrigem = frete.getCepOrigem();
        this.cepDestino = frete.getCepDestino();
        this.totalFrete = frete.getTotalFrete();
        this.dataPrevistaEntrega = frete.getDataPrevistaEntrega();
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public Double getTotalFrete() {
        return totalFrete;
    }

    public String getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

}

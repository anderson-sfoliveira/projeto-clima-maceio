package br.com.andersonoliveira.projetoclimamaceio.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultClimaApi {

    @JsonProperty(value = "temp")
    private int temperatura;

    @JsonProperty(value = "description")
    private String descricao;

    @JsonProperty(value = "humidity")
    private int umidade;

    @JsonProperty(value = "cloudiness")
    private int nebulosidade;

    @JsonProperty(value = "rain")
    private int volumeChuva;

    @JsonProperty(value = "wind_speedy")
    private String velocidadeVento;
}
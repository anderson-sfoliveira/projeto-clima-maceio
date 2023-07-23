package br.com.andersonoliveira.projetoclimamaceio.client;

import br.com.andersonoliveira.projetoclimamaceio.client.model.ResultClimaApi;
import br.com.andersonoliveira.projetoclimamaceio.client.exception.ClimaApiException;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ClimaApiClient {

    @Value("${hgbrasil.key}")
    private String apiKey;

    private final ModelMapper modelMapper;

    public DadosClimaticosDTO obterDadosClima() {
        final String urlApiClima = "https://api.hgbrasil.com/weather?" +
                "fields=only_results,temp,description,humidity,cloudiness,rain,wind_speedy" +
                "&key=" + apiKey +
                "&city_name=Maceio,AL";

        try {

            RestTemplate restTemplate = new RestTemplate();
            ResultClimaApi respostaApi = restTemplate.getForObject(urlApiClima, ResultClimaApi.class);

            DadosClimaticosDTO dadosClimaticosDTO = modelMapper.map(respostaApi, DadosClimaticosDTO.class);
            dadosClimaticosDTO.setDataBusca(LocalDateTime.now());

            return dadosClimaticosDTO;

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ClimaApiException("Cidade não encontrada na API de clima");
            } else {
                throw new ClimaApiException("Erro ao fazer a chamada à API de clima: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new ClimaApiException("Erro ao obter dados de clima: " + e.getMessage());
        }
    }
}

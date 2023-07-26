package br.com.andersonoliveira.projetoclimamaceio.service.impl;

import br.com.andersonoliveira.projetoclimamaceio.client.ClimaApiClient;
import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final DadosClimaticosServiceImpl dadosClimaServiceImpl;
    private final ClimaApiClient climaApiClient;

    @Override
    @Scheduled(cron = "${cron.expression}")
    public void buscarDadosClimaticos() {
        DadosClimaticosDTO dadosClimaticosDTO = climaApiClient.obterDadosClima();
        dadosClimaServiceImpl.salvar(DadosClimaticos.create(dadosClimaticosDTO));
    }
}

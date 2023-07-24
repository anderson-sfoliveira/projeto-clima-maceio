package br.com.andersonoliveira.projetoclimamaceio.service;

import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;

import java.time.LocalDate;
import java.util.List;

public interface DadosClimaticosService {

    public DadosClimaticos salvar(DadosClimaticos dadosClimaticos);
    public List<DadosClimaticosDTO> buscarDadosPorIntervaloData(LocalDate dataInicial, LocalDate dataFinal);
}

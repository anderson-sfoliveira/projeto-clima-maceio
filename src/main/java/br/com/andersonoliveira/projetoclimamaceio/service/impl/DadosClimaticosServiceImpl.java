package br.com.andersonoliveira.projetoclimamaceio.service.impl;

import br.com.andersonoliveira.projetoclimamaceio.service.exception.DadosClimaticosException;
import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.repository.DadosClimaticosRepository;
import br.com.andersonoliveira.projetoclimamaceio.service.DadosClimaticosService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadosClimaticosServiceImpl implements DadosClimaticosService {

    private final DadosClimaticosRepository dadosClimaticosRepository;

    @Override
    public DadosClimaticos salvar(DadosClimaticos dadosClimaticos) {
        try {
            return dadosClimaticosRepository.save(dadosClimaticos);
        } catch (DataAccessException e) {
            throw new DadosClimaticosException("Erro ao salvar dados climáticos: " + e.getMessage());
        }
    }

    @Override
    public List<DadosClimaticosDTO> buscarDadosPorIntervaloData(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial == null) {
            throw new DadosClimaticosException("Erro ao buscar dados climáticos no banco de dados: Data inicial é obrigatória!");
        }

        if (dataFinal == null) {
            throw new DadosClimaticosException("Erro ao buscar dados climáticos no banco de dados: Data final é obrigatória!");
        }

        try {
            LocalDateTime dataInicialComHoraZero = dataInicial.atStartOfDay();
            LocalDateTime dataFinalComHoraFinal = dataFinal.atTime(23, 59, 59);

            List<DadosClimaticos> dadosClimaticosList = dadosClimaticosRepository.findByDataBuscaBetween(dataInicialComHoraZero, dataFinalComHoraFinal);
            return dadosClimaticosList.stream()
                    .map(DadosClimaticos::toDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new DadosClimaticosException("Erro ao buscar dados climáticos no banco de dados: " + e.getMessage());
        }
    }
}

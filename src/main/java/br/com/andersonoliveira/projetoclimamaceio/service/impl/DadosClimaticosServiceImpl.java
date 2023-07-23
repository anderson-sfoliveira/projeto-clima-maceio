package br.com.andersonoliveira.projetoclimamaceio.service.impl;

import br.com.andersonoliveira.projetoclimamaceio.service.exception.DadosClimaticosSalvamentoException;
import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.repository.DadosClimaticosRepository;
import br.com.andersonoliveira.projetoclimamaceio.service.DadosClimaticosService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadosClimaticosServiceImpl implements DadosClimaticosService {

    private final DadosClimaticosRepository dadosClimaticosRepository;
    private final ModelMapper modelMapper;

    public DadosClimaticos salvar(DadosClimaticos dadosClimaticos) {
        try {
            return dadosClimaticosRepository.save(dadosClimaticos);
        } catch (DataAccessException e) {
            throw new DadosClimaticosSalvamentoException("Erro ao salvar dados climáticos: " + e.getMessage());
        }
    }

    public List<DadosClimaticosDTO> buscarTodosDados() {
        List<DadosClimaticos> dadosClimaticos = dadosClimaticosRepository.findAll();

        List<DadosClimaticosDTO> dadosClimasDTO = dadosClimaticos.stream()
                .map(dadosClima -> modelMapper.map(dadosClima, DadosClimaticosDTO.class))
                .collect(Collectors.toList());

        return dadosClimasDTO;
    }
}

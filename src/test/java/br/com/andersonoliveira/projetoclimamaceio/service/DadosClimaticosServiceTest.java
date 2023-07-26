package br.com.andersonoliveira.projetoclimamaceio.service;

import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.repository.DadosClimaticosRepository;
import br.com.andersonoliveira.projetoclimamaceio.service.exception.DadosClimaticosException;
import br.com.andersonoliveira.projetoclimamaceio.service.impl.DadosClimaticosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DadosClimaticosServiceTest {

    @InjectMocks
    DadosClimaticosServiceImpl service;

    @Mock
    DadosClimaticosRepository repository;

    DadosClimaticos dadosClimaticos;
    DadosClimaticosDTO dadosClimaticosDTO;

    @BeforeEach
    public void setUp() {
        dadosClimaticos = new DadosClimaticos(1L, 25, "Tempo nublado", 78, 75, 0, "3.09 km/h", LocalDate.of(2023, 7, 1).atTime(12, 00, 00));
        dadosClimaticosDTO = new DadosClimaticosDTO(25, "Tempo nublado", 78, 75, 0, "3.09 km/h", LocalDate.of(2023, 7, 1).atTime(12, 00, 00));
    }

    @Test
    public void testBuscarDadosPorIntervaloData() {
        LocalDate dataInicial = LocalDate.of(2023, 7, 1);
        LocalDate dataFinal = LocalDate.of(2023, 7, 1);

        LocalDateTime dataInicialComHoraZero = dataInicial.atStartOfDay();
        LocalDateTime dataFinalComHoraFinal = dataFinal.atTime(23, 59, 59);

        when(repository.findByDataBuscaBetween(dataInicialComHoraZero, dataFinalComHoraFinal))
                .thenReturn(Collections.singletonList(dadosClimaticos));

        List<DadosClimaticosDTO> resultado = service.buscarDadosPorIntervaloData(dataInicial, dataFinal);

        assertEquals(Collections.singletonList(dadosClimaticosDTO).size(), resultado.size());
        verify(repository).findByDataBuscaBetween(dataInicialComHoraZero, dataFinalComHoraFinal);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void naoDeveChamaroRepositoryCasoParametroDataInicialNulo() {
        final DadosClimaticosException e = assertThrows(DadosClimaticosException.class, () -> {
            service.buscarDadosPorIntervaloData(null, LocalDate.of(2023, 7, 10));
        });

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), is("Erro ao buscar dados climáticos no banco de dados: Data inicial é obrigatória!"));
        verifyNoInteractions(repository);
    }

    @Test
    public void testSalvar() {
        DadosClimaticos dadosClimaticos = new DadosClimaticos();

        when(repository.save(dadosClimaticos))
                .thenReturn(dadosClimaticos);

        DadosClimaticos resultado = service.salvar(dadosClimaticos);

        assertEquals(dadosClimaticos, resultado);
        verify(repository).save(dadosClimaticos);
        verifyNoMoreInteractions(repository);
    }
}
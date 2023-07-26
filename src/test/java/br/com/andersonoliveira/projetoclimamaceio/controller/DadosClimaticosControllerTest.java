package br.com.andersonoliveira.projetoclimamaceio.controller;

import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.service.impl.DadosClimaticosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class DadosClimaticosControllerTest {

    @InjectMocks
    DadosClimaticosController controller;

    @Mock
    private DadosClimaticosServiceImpl service;

    MockMvc mockMvc;

    private DadosClimaticosDTO dadosClimaticosDTO;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();

        dadosClimaticosDTO = new DadosClimaticosDTO(25, "Tempo nublado", 78, 75, 0, "3.09 km/h", LocalDate.of(2023, 7, 1).atTime(12, 00, 00));
        dataInicial = LocalDate.of(2023, 7, 1);
        dataFinal = LocalDate.of(2023, 7, 1);
    }

    @Test
    public void deveBuscarDadosPorIntervaloDataComSucesso() throws Exception {
        when(service.buscarDadosPorIntervaloData(dataInicial, dataFinal)).thenReturn(Collections.singletonList(dadosClimaticosDTO));

        mockMvc.perform(get("/dados-climaticos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dataInicial", dataInicial.toString())
                        .param("dataFinal", dataFinal.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(service).buscarDadosPorIntervaloData(dataInicial, dataFinal);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void deveRetonrarErroxxCasoNaoPassadosParametrosObrigatorios() throws Exception {

        mockMvc.perform(get("/dados-climaticos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dataInicial", dataInicial.toString()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();

        verifyNoInteractions(service);

    }
}

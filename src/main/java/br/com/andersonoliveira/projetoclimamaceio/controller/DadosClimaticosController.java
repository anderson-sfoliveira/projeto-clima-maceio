package br.com.andersonoliveira.projetoclimamaceio.controller;


import br.com.andersonoliveira.projetoclimamaceio.client.ClimaApiClient;
import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.service.impl.DadosClimaticosServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dados-climaticos")
@RequiredArgsConstructor
public class DadosClimaticosController {

    private final DadosClimaticosServiceImpl dadosClimaServiceImpl;
    private final ClimaApiClient climaApiClient;

    @GetMapping
//    @Operation(summary = "Find loan by params")
    public ResponseEntity<List<DadosClimaticosDTO>> buscaDados() {
        return ResponseEntity.ok(dadosClimaServiceImpl.buscarTodosDados());
    }
}

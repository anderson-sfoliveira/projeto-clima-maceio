package br.com.andersonoliveira.projetoclimamaceio.controller;

import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.service.impl.DadosClimaticosServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dados-climaticos")
@RequiredArgsConstructor
public class DadosClimaticosController {

    private final DadosClimaticosServiceImpl dadosClimaServiceImpl;

    @GetMapping
//    @Operation(summary = "Find loan by params")
    public ResponseEntity<List<DadosClimaticosDTO>> buscarDadosPorIntervaloData(
            @RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal) {

        if (dataInicial.isAfter(dataFinal)) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }

        List<DadosClimaticosDTO> dadosClimaticosDTOList = dadosClimaServiceImpl.buscarDadosPorIntervaloData(dataInicial, dataFinal);
        return ResponseEntity.ok(dadosClimaticosDTOList);
    }
}

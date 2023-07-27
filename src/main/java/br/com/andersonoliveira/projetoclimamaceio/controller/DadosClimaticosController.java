package br.com.andersonoliveira.projetoclimamaceio.controller;

import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import br.com.andersonoliveira.projetoclimamaceio.service.impl.DadosClimaticosServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/dados-climaticos", produces = {"application/json"})
@Tag(name = "dados-climaticos")
@RequiredArgsConstructor
public class DadosClimaticosController {

    private final DadosClimaticosServiceImpl dadosClimaServiceImpl;

    @Operation(summary = "Busca dados do clima de Maceió por intervalo de data", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public ResponseEntity<List<DadosClimaticosDTO>> buscarDadosPorIntervaloData(
            @RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal) {

        if (dataInicial.isAfter(dataFinal)) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }

        List<DadosClimaticosDTO> dadosClimaticosDTOList = dadosClimaServiceImpl.buscarDadosPorIntervaloData(dataInicial, dataFinal);
        return ResponseEntity.ok(dadosClimaticosDTOList);
    }
}

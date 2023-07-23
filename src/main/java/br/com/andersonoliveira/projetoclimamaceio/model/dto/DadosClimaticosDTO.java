package br.com.andersonoliveira.projetoclimamaceio.model.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosClimaticosDTO {

    private int temperatura;
    private String descricao;
    private int umidade;
    private int nebulosidade;
    private int volumeChuva;
    private String velocidadeVento;
    private LocalDateTime dataBusca;
}
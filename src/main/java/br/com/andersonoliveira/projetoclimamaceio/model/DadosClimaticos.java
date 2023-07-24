package br.com.andersonoliveira.projetoclimamaceio.model;

import br.com.andersonoliveira.projetoclimamaceio.model.dto.DadosClimaticosDTO;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;

@Entity
@Table(name = "dados_climaticos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosClimaticos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int temperatura;

    @Column(length = 255)
    private String descricao;
    private int umidade;
    private int nebulosidade;

    @Column(name = "volume_chuva")
    private int volumeChuva;

    @Column(name = "velocidade_vento", length = 255)
    private String velocidadeVento;

    @Column(name = "data_busca")
    private LocalDateTime dataBusca;

    public static DadosClimaticos create(DadosClimaticosDTO dadosClimaticosDTO) {
        return new ModelMapper().map(dadosClimaticosDTO, DadosClimaticos.class);
    }

    public static DadosClimaticosDTO toDTO(DadosClimaticos dadosClimaticos) {
        return new ModelMapper().map(dadosClimaticos, DadosClimaticosDTO.class);
    }
}
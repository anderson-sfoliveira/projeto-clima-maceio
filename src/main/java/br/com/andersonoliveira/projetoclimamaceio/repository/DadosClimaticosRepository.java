package br.com.andersonoliveira.projetoclimamaceio.repository;

import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DadosClimaticosRepository extends JpaRepository<DadosClimaticos, Long> {

    List<DadosClimaticos> findByDataBuscaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
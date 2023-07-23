package br.com.andersonoliveira.projetoclimamaceio.repository;

import br.com.andersonoliveira.projetoclimamaceio.model.DadosClimaticos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosClimaticosRepository extends JpaRepository<DadosClimaticos, Long> {
}
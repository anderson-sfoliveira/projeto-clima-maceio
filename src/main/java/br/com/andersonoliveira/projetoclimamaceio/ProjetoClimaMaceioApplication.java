package br.com.andersonoliveira.projetoclimamaceio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetoClimaMaceioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoClimaMaceioApplication.class, args);
	}

}

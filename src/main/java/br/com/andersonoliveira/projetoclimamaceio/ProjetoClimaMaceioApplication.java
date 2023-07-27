package br.com.andersonoliveira.projetoclimamaceio;

import br.com.andersonoliveira.projetoclimamaceio.model.User;
import br.com.andersonoliveira.projetoclimamaceio.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Clima em Maceió, Alagoas", version = "1", description = "Projeto para consulta e registro dos dados do clima de Maceió, Alagoas"))
@EnableScheduling
public class ProjetoClimaMaceioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoClimaMaceioApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> {
			initUsers(userRepository, bCryptPasswordEncoder);
		};
	}

	public void initUsers(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		User admin = new User();
		admin.setUserName("oliveira");
		admin.setPassword(bCryptPasswordEncoder.encode("123456"));

		User find = userRepository.findByUserName("oliveira");
		if (find == null) {
			userRepository.save(admin);
		}
	}

}

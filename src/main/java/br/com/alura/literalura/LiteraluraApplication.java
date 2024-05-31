package br.com.alura.literalura;

import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LivrosRepository repositorio;

	@Autowired
	private AutorRepository repositorio2;

	@Autowired
	private Environment env;
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio, repositorio2, env);
		principal.exibeMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}
}

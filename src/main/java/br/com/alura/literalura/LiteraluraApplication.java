package br.com.alura.literalura;

import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.model.DadosResultados;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("http://gutendex.com/books/?search=dom+casmurro");
		System.out.println("Resultado" + json);

//		//verificarApi.buscarLivrosApi();
//		System.out.println(verificarApi.buscarLivrosApi());

		ConverteDados conversor = new ConverteDados();
		DadosResultados dados = conversor.obterDados(json, DadosResultados.class);

		System.out.println(dados);
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}


}

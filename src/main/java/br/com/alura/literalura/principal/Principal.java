package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivrosRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/?search=";

    private List<Livros> listaDeLivros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    private LivrosRepository repositorio;
    private AutorRepository repositorio2;

    private Environment env;

    public Principal(LivrosRepository repositorio, AutorRepository repositorio2, Environment env) {
        this.repositorio = repositorio;
        this.repositorio2 = repositorio2;
        this.env = env;
    }


    public void exibeMenu(){

        var opcao = -1;
        while (opcao != 0){
            System.out.println("""
                ---------------------------------------
                BEM VINDO(A) AO NOSSO SISTEMA DE LIVROS 
                
                Escolha o número de sua opção:
                
                1- Buscar livro pelo título
                2- Listar livros pelo título
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                0- Sair
                """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosPeloTitulo();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...Obrigada por utilizar nossos serviços");
                    break;
                default:
                    System.out.println("Opção inválida, digite um número correto.");
                    break;
            }
        }
    }

    private void buscarLivroPeloTitulo(){
        DadosLivros dados = getDadosLivros();

        if(dados != null){
            // Transformando os dados obtidos na Classe Livros
            Livros livro = new Livros(dados);

            // Transformando os dados obtidos na Classe Autor
            for(DadosAutor dadosAutor : dados.autores()){

                // Verificando se o autor já se encontra armazenado no banco de dados
                Optional<Autor> autorExistente = repositorio2.findByAutor(dadosAutor.nome());

                Autor autor;

                if(autorExistente.isPresent()){
                    autor = autorExistente.get();
                } else{
                    autor = new Autor(dadosAutor, dados.titulo());
                    repositorio2.save(autor);
                }
                livro.setAutor(autor);
            }

            try {
                repositorio.save(livro);
                System.out.println(livro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Não é possível armazenar este livro, pois ele já está registrado. Realize a visualização com a opção de listagem.");
                resetarSequencia();
            }
        }
    }

    // Função para resetar sequencia de id's de dados já armazenados
    private void resetarSequencia() {
        String url = env.getProperty("spring.datasource.url");
        String user = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        try {
            assert url != null;
            try (Connection connection = DriverManager.getConnection(url, user, password);
                     Statement statement = connection.createStatement()) {

                String sql = "SELECT setval('livros_id_seq', (SELECT MAX(id) FROM livros))";
                statement.execute(sql);

            }
        } catch (SQLException e) {
            System.out.println("Erro para resetar a sequência: " + e.getMessage());;
        }
    }

    private DadosLivros getDadosLivros() {
        System.out.println("Insira o nome do livro que você deseja buscar:");
        var nomeLivro = scanner.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));

        DadosResultados dados = conversor.obterDados(json, DadosResultados.class);

        if(dados.resultados().isEmpty()){
            System.out.println("Nenhum livro encontrado com o título fornecido");
            return null;
        }

        return dados.resultados().get(0);
    }

    private void listarLivrosPeloTitulo() {
        listaDeLivros = repositorio.findAll();
        listaDeLivros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = repositorio2.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Insira o ano que gostaria de realizar a busca: ");
        var anoDigitado = scanner.nextInt();
        scanner.nextLine();

        autores = repositorio2.findAll();
//         utilizando lista de stream
//        List<Autor> autoresVivos = autores.stream()
//                .filter(autor -> autor.getAnoNascimento() <= anoDigitado && (autor.getAnoFalecimento() == -1 || autor.getAnoFalecimento() > anoDigitado))
//                .collect(Collectors.toList());

        // Ajustado para Media queries
        List<Autor> autoresVivos = repositorio2.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(anoDigitado, anoDigitado);

        if (!autoresVivos.isEmpty()) {
            autoresVivos.forEach(System.out::println);
        } else {
            System.out.println("Não há autores vivos em " + anoDigitado + " de acordo com os livros buscados.");
        }
    }

    private void listarLivrosPorIdioma() {
        String opcao;
        while(true){
            System.out.println("""
                    Insira o idioma para realizar a busca:
                    
                    es - Espanhol
                    en - Inglês
                    fr - Francês
                    pt - Português
                    """);
            opcao = scanner.nextLine().trim().toLowerCase();

            String finalOpcao = opcao;

            listaDeLivros = repositorio.findAll();

//           Utilizando stream
//            var livrosPorIdioma = listaDeLivros.stream()
//                    .filter(livro -> livro.getIdioma().equalsIgnoreCase(finalOpcao))
//                    .collect(Collectors.toList());

            // Ajustado para Media queries (deu certo de primeira uhull kkk)
            Optional<Livros> livrosPorIdioma = repositorio.findByIdiomaContainingIgnoreCase(finalOpcao);

            if(livrosPorIdioma.isPresent()){
                System.out.println(livrosPorIdioma.get());
            } else {
                System.out.println("Nenhum livro encontrado para o idioma selecionado.");
            }
            break;
        }
    }
}

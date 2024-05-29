package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/?search=";

    private List<Livros> listaDeLivros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

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
            listaDeLivros.add(livro);

            // Transformando os dados obtidos na Classe Autor
            for(DadosAutor dadosAutor : dados.autores()){
                Autor autor = new Autor(dadosAutor, dados.titulo());
                autores.add(autor);
            }

            System.out.println(livro);
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
        listaDeLivros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Insira o ano que gostaria de realizar a busca: ");
        var anoDigitado = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresVivos = autores.stream()
                .filter(autor -> autor.getAnoNascimento() <= anoDigitado && (autor.getAnoFalecimento() == -1 || autor.getAnoFalecimento() > anoDigitado))
                .collect(Collectors.toList());

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
            var livrosPorIdioma = listaDeLivros.stream()
                    .filter(livro -> livro.getIdioma().equalsIgnoreCase(finalOpcao))
                    .collect(Collectors.toList());

            if(!livrosPorIdioma.isEmpty()){
                livrosPorIdioma.forEach(System.out::println);
                break;
            } else {
                System.out.println("Nenhum livro encontrado para o idioma selecionado.");
            }
        }
    }
}

package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.model.DadosResultados;
import br.com.alura.literalura.model.Livros;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://gutendex.com/books/?search=";
    //"http://gutendex.com/books/?search=dom+casmurro"
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
            Livros livros = new Livros(dados);
            System.out.println(livros);
        }else {
            System.out.println("Nenhum livro encontrado.");
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
    }

    private void listarAutoresRegistrados() {
    }

    private void listarAutoresVivosPorAno() {
    }

    private void listarLivrosPorIdioma() {
    }
}

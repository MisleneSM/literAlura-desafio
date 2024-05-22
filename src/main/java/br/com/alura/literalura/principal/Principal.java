package br.com.alura.literalura.principal;

import java.util.Scanner;

public class Principal {
    private final String ENDERECO = "http://gutendex.com/books/?search=";
    //"http://gutendex.com/books/?search=dom+casmurro"
    Scanner scanner = new Scanner(System.in);

    public void exibeMenu(){

        var opcao = -1;
        while (opcao != 0){
            System.out.println("""
                ----------------------------
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

    private void buscarLivroPeloTitulo() {

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

package br.com.alura.literalura.model;

import java.util.stream.Collectors;

public class Livros {
    private String autor;
    private String idioma;
    private Integer numeroDownloads;
    private String titulo;

    public Livros (DadosLivros dadosLivros) {
        this.autor = dadosLivros.autores().stream().map(DadosAutor::nome).collect(Collectors.joining(", "));
        this.idioma = String.join(", ", dadosLivros.idiomas());
        this.titulo = dadosLivros.titulo();
        this.numeroDownloads = dadosLivros.numeroDonwloads();
    }

    public String getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "------ LIVRO ------\n" +
                "Titulo: " + getTitulo() + "\n" +
                "Autor: " + getAutor() + "\n" +
                "Idioma: " + getIdioma() + "\n" +
                "Número de downloads: " + getNumeroDownloads();
    }
}
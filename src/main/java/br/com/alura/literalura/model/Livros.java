package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;
    private String idioma;
    private Integer numeroDownloads;
    private String titulo;

    public Livros() {
    }

    public Livros (DadosLivros dadosLivros) {
        this.autor = dadosLivros.autores().stream().map(DadosAutor::nome).collect(Collectors.joining(", "));
        this.idioma = dadosLivros.idiomas().isEmpty() ? "" : dadosLivros.idiomas().get(0);
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
                "Número de downloads: " + getNumeroDownloads() + "\n";
    }
}
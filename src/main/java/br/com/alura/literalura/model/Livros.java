package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String autores;
    private String idioma;
    private Integer numeroDownloads;

    @Column(unique = true)
    private String titulo;

    // Essas cardinalidades precisam ter nomes diferentes dos atributos, pois se não o mesmo irá gerar novas colunas no banco de dados
    @ManyToOne
    private Autor autor;

    public Livros() {
    }

    public Livros (DadosLivros dadosLivros) {
        this.autores = dadosLivros.autores().stream().map(DadosAutor::nome).collect(Collectors.joining(", "));
        this.idioma = dadosLivros.idiomas().isEmpty() ? "" : dadosLivros.idiomas().get(0);
        this.titulo = dadosLivros.titulo();
        this.numeroDownloads = dadosLivros.numeroDonwloads();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autores) {
        this.autor = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAutores() {
        return autores;
    }

    public void setAutores(String autor) {
        this.autores = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    @Override
    public String toString() {
        return "------ LIVRO ------\n" +
                "Titulo: " + getTitulo() + "\n" +
                "Autor: " + getAutores() + "\n" +
                "Idioma: " + getIdioma() + "\n" +
                "Número de downloads: " + getNumeroDownloads() + "\n";
    }
}
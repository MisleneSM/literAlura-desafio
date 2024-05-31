package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String autor;
    private int anoNascimento;
    private int anoFalecimento;

    private String livros;

    // Cascade faz a persistencia dos dados e insere
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livros> listaLivros = new ArrayList<>();

    public Autor() {}

    public Autor(DadosAutor dadosAutor, String livro){
        this.autor = dadosAutor.nome();
        this.anoNascimento = dadosAutor.dataNascimento();
        this.anoFalecimento = dadosAutor.dataFalecimento();
        this.livros = livro;
    }

    public List<Livros> getListaLivros() {
        return listaLivros;
    }

    // Associação de chave estrangeira
    public void setListaLivros(List<Livros> listaLivros) {
        listaLivros.forEach( l -> l.setAutor(this));
        this.listaLivros = listaLivros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public int getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(int anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public String getLivros() {
        return livros;
    }

    public void setLivros(String livros) {
        this.livros = livros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(this.autor, autor.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autor);
    }

    @Override
    public String toString() {
        return
                "Autor: " + getAutor() + '\n' +
                "Ano de nascimento: " + getAnoNascimento() + "\n" +
                "Ano de falecimento: " + getAnoFalecimento() + "\n" +
                "Livros: " + "[ " + getLivros() + " ]" + "\n";
    }
}

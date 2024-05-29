package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autor;
    private int anoNascimento;
    private int anoFalecimento;

    private String livros;

    public Autor() {}

    public Autor(DadosAutor dadosAutor, String livro){
        this.autor = dadosAutor.nome();
        this.anoNascimento = dadosAutor.dataNascimento();
        this.anoFalecimento = dadosAutor.dataFalecimento();
        this.livros = livro;
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
    public String toString() {
        return
                "Autor: " + getAutor() + '\n' +
                "Ano de nascimento: " + getAnoNascimento() + "\n" +
                "Ano de falecimento: " + getAnoFalecimento() + "\n" +
                "Livros: " + "[ " + getLivros() + " ]" + "\n";
    }
}

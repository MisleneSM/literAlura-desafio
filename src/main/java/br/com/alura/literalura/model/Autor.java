package br.com.alura.literalura.model;

public class Autor {
    private String autor;
    private int anoNascimento;
    private int anoFalecimento;

    private String livros;

    public Autor(DadosAutor dadosAutor){
        this.autor = dadosAutor.nome();
        this.anoNascimento = dadosAutor.dataNascimento();
        this.anoFalecimento = dadosAutor.dataFalecimento();
        this.livros = dadosAutor.livros();
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
        return "Autor='" + getAutor() + '\n' +
                "Ano de nascimento: " + getAnoNascimento() + "\n" +
                "Ano de falecimento: " + getAnoFalecimento() +
                "Livros: " + "[ " + getLivros() + " ]";
    }
}

package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("id") Long id,
                          @JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<DadosAutor> autores,
                          @JsonAlias("languages") List<String> idiomas,
                          @JsonAlias("download_count") Integer numeroDonwloads
                          ) {
}

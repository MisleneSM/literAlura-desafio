package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivrosRepository extends JpaRepository<Livros, Long> {
    Optional<Livros> findByIdiomaContainingIgnoreCase(String idioma);

}

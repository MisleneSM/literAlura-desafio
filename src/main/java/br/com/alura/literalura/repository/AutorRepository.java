package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(int anoDigitado, int anoDigitado1);

    // Verifica se o autor já se encontra armazenado no banco de dados
    Optional<Autor> findByAutor(String autor);
}

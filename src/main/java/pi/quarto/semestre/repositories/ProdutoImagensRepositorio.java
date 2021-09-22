package pi.quarto.semestre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.quarto.semestre.models.ProdutoImagens;

@Repository
public interface ProdutoImagensRepositorio extends JpaRepository<ProdutoImagens, Long> {

}
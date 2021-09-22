package pi.quarto.semestre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.quarto.semestre.models.Produto;

@Repository
public interface ProdutoImagensRepositorio extends JpaRepository<Produto, Long> {

}
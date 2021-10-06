package pi.quarto.semestre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pi.quarto.semestre.models.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

	List<Produto> findAllProdutoByNameIsNotNull();
}
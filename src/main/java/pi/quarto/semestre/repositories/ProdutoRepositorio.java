package pi.quarto.semestre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pi.quarto.semestre.models.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

	@Query( value = "select * from Produto p where p.status = true", nativeQuery = true)
	public List<Produto>findByStatus(boolean status );
	
}
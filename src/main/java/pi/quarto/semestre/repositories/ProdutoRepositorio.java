package pi.quarto.semestre.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pi.quarto.semestre.models.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
	@Query( value = "select * from Produto p where p.status = true", nativeQuery = true)
	public List<Produto>findByStatus(boolean status );
	
	@Query( value = "select * from Produto where id = :id", nativeQuery = true)
	Produto findProdutoById(long id);
}
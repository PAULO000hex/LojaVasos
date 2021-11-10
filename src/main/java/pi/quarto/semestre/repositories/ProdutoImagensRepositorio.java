package pi.quarto.semestre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pi.quarto.semestre.models.ProdutoImagens;

@Repository
public interface ProdutoImagensRepositorio extends JpaRepository<ProdutoImagens, Long> {
	
	@Query(value="select * from produto_imagens where id_produto = :id_produto", nativeQuery = true)
	public List<ProdutoImagens> findImagemByIdProduto(long id_produto);

}
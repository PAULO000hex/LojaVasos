package pi.quarto.semestre.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import pi.quarto.semestre.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
	@Query(value="select * from pedido where cliente_id = :cliente_id", nativeQuery = true)
	public List<Pedido> findPedidoById(long cliente_id);
	
	@Query(value="drop table pedido;", nativeQuery = true)
	public String apagarTabela();
}

package pi.quarto.semestre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pi.quarto.semestre.models.ItensCompra;


public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long> {
	@Query(value="select * from itens_compra where id_pedido = :id_pedido", nativeQuery = true)
	public List<ItensCompra> findByPedido(long id_pedido);
}

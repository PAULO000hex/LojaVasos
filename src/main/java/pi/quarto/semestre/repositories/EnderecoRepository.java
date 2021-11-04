package pi.quarto.semestre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pi.quarto.semestre.models.Endereco;

@Repository
@Transactional
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
	
	@Query(value = "select * from endereco where pessoa_id=1", nativeQuery = true)
	public List<Endereco> getEnderecos(Long pessoaid);
		
	@Query(value="select * from endereco where clienteid = :clienteid and status=true", nativeQuery = true)
	public List<Endereco> findEnderecoById(long clienteid);

	@Query(value="UPDATE endereco SET status=false WHERE id = :enderecoId", nativeQuery = true)
	public void desativarEndereco(long enderecoId);
	
}

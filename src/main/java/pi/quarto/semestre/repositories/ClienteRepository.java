package pi.quarto.semestre.repositories;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pi.quarto.semestre.models.Cliente;

@Transactional
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	
	
	@Query("select c from Cliente c where c.email = :email")
	public Cliente findByEmail(String email);
	
	@Query(value="select * from Cliente where email = :email and senha= :senha", nativeQuery = true)
	public Cliente Login(String email, String senha);

@Query(value="select * from Cliente where email = :email", nativeQuery = true)
public Cliente Login2(String email);

@Query(value="select * from Cliente where id = :id", nativeQuery = true)
public Cliente findUsuarioById(long id);

	
}

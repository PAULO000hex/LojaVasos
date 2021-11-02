package pi.quarto.semestre.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pi.quarto.semestre.models.Funcionario;

import java.util.Optional;

@Transactional
@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
	

		@Query("select f from Funcionario f where f.email = :email")
		public Funcionario findByEmail(String email);
		
		@Query(value="select * from Funcionario where email = :email and senha= :senha", nativeQuery = true)
		public Funcionario Login(String email, String senha);

	@Query(value="select * from Funcionario where email = :email", nativeQuery = true)
	public Funcionario Login2(String email);

	@Query(value="select * from Funcionario where id = :id", nativeQuery = true)
	public Funcionario findUsuarioById(long id);
	
}

package pi.quarto.semestre.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pi.quarto.semestre.models.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}

package pi.quarto.semestre;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pi.quarto.semestre.models.Pessoa;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.PessoaRepository;
import pi.quarto.semestre.repositories.ProdutoRepositorio;
import pi.quarto.semestre.util.Util;

@Component
@Transactional
public class IniciandoBanco implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void run(String... args) throws Exception {

//		Pessoa p1 = new Pessoa("admin","admin@admin","",Util.md5("admin"), true,false, true);
//		pessoaRepository.save(p1);

	}
}
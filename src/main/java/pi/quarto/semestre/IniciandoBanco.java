package pi.quarto.semestre;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pi.quarto.semestre.models.Funcionario;
import pi.quarto.semestre.repositories.FuncionarioRepository;
import pi.quarto.semestre.util.Util;

@Component
@Transactional
public class IniciandoBanco implements CommandLineRunner {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public void run(String... args) throws Exception {

	//Funcionario p1 = new Funcionario("admin","admin@admin","",Util.md5("admin"), true,false, true);
	//funcionarioRepository.save(p1);

	}
}
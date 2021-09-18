package pi.quarto.semestre;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Component
@Transactional
public class IniciandoBanco implements CommandLineRunner {

	@Autowired
	private ProdutoRepositorio produtoRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Produto p1 = new Produto();
		p1.setColor("preto");
		p1.setPrice(10.99);
		p1.setDescription("Rosa");
		
		Produto p2 = new Produto();
		p2.setColor("vermelho");
		p2.setPrice(89.99);
		p2.setDescription("Branco");
		
		produtoRepo.save(p1);
		produtoRepo.save(p2);
	}
}
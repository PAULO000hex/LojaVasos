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
		p1.setName("Produto1");
		p1.setDescription("Rosa");
		p1.setSize("98.28");
		p1.setPrice(10.99);
		p1.setColor("preto");
		p1.setAvailable(10);
		p1.setStatus(true);
		
		Produto p2 = new Produto();
		p2.setName("Produto2");
		p2.setDescription("Branco");
		p2.setSize("18.9");
		p2.setPrice(67.99);
		p2.setColor("Lilás");
		p2.setAvailable(29);
		p2.setStatus(true);
		
		produtoRepo.save(p1);
		produtoRepo.save(p2);
	}
}
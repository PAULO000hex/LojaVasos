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
		Produto p1 = new Produto("Produto1","Branco","18.9",67.99,"Lilás",29,true,"circulo.jpg");
		Produto p2 = new Produto("Produto2","Preto","18.9",67.99,"Yelloow",29,true,"circulo.jpg");
		Produto p3 = new Produto("Produto3","Amarelo","18.9",67.99,"Lilás",29,true,"circulo.jpg");
		Produto p4 = new Produto("Produto4","Verde","18.9",67.99,"Preto",29,true,"circulo.jpg");
		Produto p5 = new Produto("Produto5","Vermelho","18.9",67.99,"Black",29,true,"circulo.jpg");
		Produto p6 = new Produto("Produto1","Branco","18.9",67.99,"Lilás",29,true,"circulo.jpg");
		Produto p7 = new Produto("Produto2","Preto","18.9",67.99,"Yelloow",29,true,"circulo.jpg");
		Produto p8 = new Produto("Produto3","Amarelo","18.9",67.99,"Lilás",29,true,"circulo.jpg");
		Produto p9 = new Produto("Produto4","Verde","18.9",67.99,"Preto",29,true,"circulo.jpg");
		Produto p10 = new Produto("Produto5","Vermelho","18.9",67.99,"Black",29,true,"circulo.jpg");
		
		produtoRepo.save(p1);
		produtoRepo.save(p2);
		produtoRepo.save(p3);
		produtoRepo.save(p4);
		produtoRepo.save(p5);
		produtoRepo.save(p6);
		produtoRepo.save(p7);
		produtoRepo.save(p8);
		produtoRepo.save(p9);
		produtoRepo.save(p10);
	}
}
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
		String descricao = "Vaso ou jarra é uma peça em forma de jarro que é utilizado para decorar salas, recepções, corredores ou ainda para decorar espaços abertos, como escadas ou jardins. Sua função é meramente ornamental, sendo utilizado para armazenar flores.";
		Produto p1 = new Produto("Terraço",descricao,"18.9",67.99,"Lilás",29,true,"circulo.jpg");
		Produto p2 = new Produto("Azeleias",descricao,"18.9",34.99,"Verde",29,true,"circulo.jpg");
		Produto p3 = new Produto("Girassol",descricao,"18.9",68.99,"Branco",29,true,"circulo.jpg");
		Produto p4 = new Produto("Kalanchoe",descricao,"18.9",97.99,"Preto",29,true,"circulo.jpg");
		Produto p5 = new Produto("Cristaleira",descricao,"18.9",27.99,"Amarelo",29,true,"circulo.jpg");
		Produto p6 = new Produto("Cactos",descricao,"18.9",21.99,"Vermelho",29,true,"circulo.jpg");
		Produto p7 = new Produto("Esmeralda",descricao,"18.9",10.99,"Transparente",29,true,"circulo.jpg");
		Produto p8 = new Produto("Vaso",descricao,"18.9",98.99,"Vermelho",29,true,"circulo.jpg");
		Produto p9 = new Produto("Vaso",descricao,"18.9",59.99,"Amarelo",29,true,"circulo.jpg");
		Produto p10 = new Produto("Vaso",descricao,"18.9",77.99,"Branco",29,true,"circulo.jpg");

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
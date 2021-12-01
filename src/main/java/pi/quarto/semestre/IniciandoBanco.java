package pi.quarto.semestre;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pi.quarto.semestre.models.Funcionario;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.FuncionarioRepository;
import pi.quarto.semestre.repositories.ProdutoRepositorio;
import pi.quarto.semestre.util.Util;

@Component
@Transactional
public class IniciandoBanco implements CommandLineRunner {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	
	@Autowired 
	private ProdutoRepositorio  produtoRepo;
	@Override
	public void run(String... args) throws Exception {

	//Funcionario p1 = new Funcionario("admin","admin@admin","",Util.md5("admin"), true,false, true);
	//funcionarioRepository.save(p1);

		
		
		/*
		 * String descricao =
		 * "Vaso ou jarra é uma peça em forma de jarro que é utilizado para decorar salas, recepções, corredores ou ainda para decorar espaços abertos, como escadas ou jardins. Sua função é meramente ornamental, sendo utilizado para armazenar flores."
		 * ; Produto p1 = new Produto("Terraço", descricao, "18.9", 67.99, "Marrom", 29,
		 * true, "vaso1.jpg"); Produto p2 = new Produto("Azeleias", descricao, "18.9",
		 * 34.99, "Bege", 29, true, "vaso2.jpg"); Produto p3 = new Produto("Palmeira",
		 * descricao, "18.9", 68.99, "Terra", 29, true, "vaso3.jpg"); Produto p4 = new
		 * Produto("Kalanchoe", descricao, "18.9", 97.99, "Preto", 29, true,
		 * "vaso4.jpg"); Produto p5 = new Produto("Cristaleira", descricao, "18.9",
		 * 27.99, "Branco", 29, true, "vaso5.jpg"); Produto p6 = new Produto("Plantio",
		 * descricao, "18.9", 21.99, "Marrom", 29, true, "vaso6.jpg"); Produto p7 = new
		 * Produto("Diversos", descricao, "18.9", 10.99, "Variado", 29, true,
		 * "vaso7.jpg"); Produto p8 = new Produto("Vaso Bola", descricao, "18.9", 98.99,
		 * "Vinho", 29, true, "vaso8.jpg");
		 * 
		 * produtoRepo.save(p1); produtoRepo.save(p2); produtoRepo.save(p3);
		 * produtoRepo.save(p4); produtoRepo.save(p5); produtoRepo.save(p6);
		 * produtoRepo.save(p7); produtoRepo.save(p8);
		 */
		
		
	}
}
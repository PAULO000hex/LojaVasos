package pi.quarto.semestre.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.ProdutoImagensRepositorio;
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class IndexController {
	
	@Autowired
	private ProdutoRepositorio produtoRepo;
	
	@Autowired
	private ProdutoImagensRepositorio produtoImagensRepo;

	@GetMapping("/index")
	public String produtos(Model model) {
		model.addAttribute("listaProdutos", produtoRepo.findByStatus(true)) ;
		return "index";
	}
	
	@GetMapping("/produto/detalhes-eccomerce/{id}")
	public String detalhesProduto(@PathVariable("id") long id, Model model) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto ==null) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		model.addAttribute("produto", produto.get());
		model.addAttribute("imagens", produtoImagensRepo.findAll());
		return "detalhesProduto";
	}
}
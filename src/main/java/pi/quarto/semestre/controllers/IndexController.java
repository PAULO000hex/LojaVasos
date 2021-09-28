package pi.quarto.semestre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class IndexController {
	
	@Autowired
	private ProdutoRepositorio produtoRepo;

	@GetMapping("/index")
	public String produtos(Model model) {
		model.addAttribute("listaProdutos", produtoRepo.findAll());
		return "index";
	}
}
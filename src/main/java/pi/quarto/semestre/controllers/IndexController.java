package pi.quarto.semestre.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.ProdutoImagensRepositorio;
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class IndexController {
	
	@Autowired
	private ProdutoRepositorio produtoRepo;
	
	@Autowired
	private ProdutoImagensRepositorio produtoImagensRepo;

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/loginCliente";
	}

	@GetMapping("/")
	public String produtos(Model model, HttpServletRequest request) {
		model.addAttribute("listaProdutos", produtoRepo.findByStatus(true) );
		model.addAttribute("id",request.getSession().getAttribute("id"));
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu(Model model, HttpServletRequest request) {
		model.addAttribute("listaProdutos", produtoRepo.findByStatus(true) );
		model.addAttribute("id",request.getSession().getAttribute("id"));
		//Comparação se é ADMIN
		boolean admin = (boolean) request.getSession().getAttribute("admin");
		
		if(admin == true) {
			//SE FOR ADMIN
			model.addAttribute("Menu", admin);
		}else {
			//SE FOR BACKOFFICE
			model.addAttribute("Menu", admin);
		}
		
		return "menu";
	}
	
	@GetMapping("/produto/detalhes-eccomerce/{id}")
	public String detalhesProduto(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		model.addAttribute("id",request.getSession().getAttribute("id"));
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto ==null) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		model.addAttribute("produto", produto.get());
		model.addAttribute("imagens", produtoImagensRepo.findAll());
		return "detalhesProduto";
	}


}
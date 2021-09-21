package pi.quarto.semestre.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class ProdutoController {

	private ProdutoRepositorio produtoRepo;
	
	public ProdutoController(ProdutoRepositorio produtoRepo) {
		this.produtoRepo = produtoRepo;
	}
	
	@GetMapping("/produtos")
	public String produtos(Model model) {
		model.addAttribute("listaProdutos", produtoRepo.findAll());
		return "produtos";
	}
	
	@GetMapping("/produto/{id}")
	public String alterarProdutos(@PathVariable("id") long id, Model model) {
		Optional<Produto> produtoOpt = produtoRepo.findById(id);
		if (produtoOpt.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		model.addAttribute("produto", produtoOpt.get());
		return "formProduto";
	}
	
	@GetMapping("/produtos/incluir")
	public String novoProduto(@ModelAttribute("produto") Produto produto) {
		return "formProduto";
	}
	
	@PostMapping("/produtos/salvar")
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult bindingResult) {		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "formProduto";
		}
		
		produto.setStatus(true);
		produtoRepo.save(produto);
		return "redirect:/produtos";
	}
	
	@GetMapping("/produtos/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		produtoRepo.delete(produto.get());
		return "redirect:/product";
	}
}
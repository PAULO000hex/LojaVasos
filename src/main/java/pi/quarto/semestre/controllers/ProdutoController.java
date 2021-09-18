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
	public String pessoas(Model model) {
		model.addAttribute("listaProdutos", produtoRepo.findAll());
		return "index";
	}
	
	@GetMapping("/produtos{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Produto> pessoaOpt = produtoRepo.findById(id);
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Produto inválida!");
		}
		
		model.addAttribute("produto", pessoaOpt.get());
		return "produtos/form";
	}
	
	@GetMapping("/produtos/incluir")
	public String novaPessoa(@ModelAttribute("produto") Produto pessoa) {
		return "produtos/form";
	}
	
	@PostMapping("/rh/pessoas/salvar")
	public String salvarPessoa(@Valid @ModelAttribute("produto") Produto pessoa, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "produtos/form";
		}
		
		produtoRepo.save(pessoa);
		return "redirect:/produtos";
	}
	
	@GetMapping("/produtos/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Produto> pessoaOpt = produtoRepo.findById(id);
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		produtoRepo.delete(pessoaOpt.get());
		return "redirect:/produtos";
	}
}
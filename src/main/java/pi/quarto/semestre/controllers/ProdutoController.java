package pi.quarto.semestre.controllers;

import java.util.Optional;

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
	
//	@GetMapping("/rh/pessoas")
//	public String pessoas(Model model) {
//		model.addAttribute("listaPessoas", produtoRepo.findAll());
//		return "rh/pessoas/index";
//	}
//	
//	@GetMapping("/rh/pessoas/{id}")
//	public String alterarPessoa(@PathVariable("id") long id, Model model) {
//		Optional<Produto> pessoaOpt = produtoRepo.findById(id);
//		if (pessoaOpt.isEmpty()) {
//			throw new IllegalArgumentException("Pessoa inválida!");
//		}
//		
//		model.addAttribute("pessoa", pessoaOpt.get());
//		return "rh/pessoas/form";
//	}
//	
//	@GetMapping("/rh/pessoas/nova")
//	public String novaPessoa(@ModelAttribute("pessoa") Produto pessoa) {
//		return "rh/pessoas/form";
//	}
//	
//	@PostMapping("/rh/pessoas/salvar")
//	public String salvarPessoa(@Valid @ModelAttribute("pessoa") Produto pessoa, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			return "rh/pessoas/form";
//		}
//		
//		produtoRepo.save(pessoa);
//		return "redirect:/rh/pessoas";
//	}
//	
//	@GetMapping("/rh/pessoas/excluir/{id}")
//	public String excluirPessoa(@PathVariable("id") long id) {
//		Optional<Produto> pessoaOpt = produtoRepo.findById(id);
//		if (pessoaOpt.isEmpty()) {
//			throw new IllegalArgumentException("Pessoa inválida!");
//		}
//		
//		produtoRepo.delete(pessoaOpt.get());
//		return "redirect:/rh/pessoas";
//	}
}
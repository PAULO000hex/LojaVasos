package pi.quarto.semestre.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.models.ProdutoImagens;
import pi.quarto.semestre.repositories.ProdutoImagensRepositorio;
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoImagensRepositorio produtoImagensRepo;
	private static String caminhoImagens = "src\\main\\resources\\static\\assets\\images\\";
	private ProdutoRepositorio produtoRepo;

	public ProdutoController(ProdutoRepositorio produtoRepo) {
		this.produtoRepo = produtoRepo;
	}

	@GetMapping("/produtos")
	public String produtos(Model model) {
		model.addAttribute("listaProdutos",
				produtoRepo.findAllProdutoByNameIsNotNull());
		return "backoffice/produtos";
	}
	
	@GetMapping("/produto/imagens/{id}")
	public String produtoImagens(@PathVariable("id") long id, Model model) {
		Optional<Produto> produtoOpt = produtoRepo.findById(id);
		if (produtoOpt==null) {
			throw new IllegalArgumentException("Produto inválido!");
		}

		model.addAttribute("produto", produtoOpt.get());
		return "backoffice/formImagens";
	}

	@PostMapping("/produto/imagem/salvar")
	public String salvarImagem(@Valid @ModelAttribute("produto") Produto produto,
			@RequestParam("file") MultipartFile arquivo) {

		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				ProdutoImagens prodImg = new ProdutoImagens(String.valueOf((produto.getId()) + arquivo.getOriginalFilename()), produto);
				produtoImagensRepo.save(prodImg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/produtos";
	}

	@GetMapping("/produto/{id}")
	public String alterarProdutos(@PathVariable("id") long id, Model model) {
		Optional<Produto> produtoOpt = produtoRepo.findById(id);
		if (produtoOpt==null) {
			throw new IllegalArgumentException("Produto inválido!");
		}

		model.addAttribute("produto", produtoOpt.get());
		return "backoffice/formProduto";
	}

	@GetMapping("/produtos/incluir")
	public String novoProduto(@ModelAttribute("produto") Produto produto) {
		return "backoffice/formProduto";
	}

	@PostMapping("/produtos/salvar")
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult bindingResult,
			@RequestParam("file") MultipartFile arquivo) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "backoffice/formProduto";
		}

		produto.setStatus(true);
		produtoRepo.save(produto);
		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				System.out.println(caminho.toAbsolutePath());
				Files.write(caminho, bytes);
				produto.setImage_url(String.valueOf((produto.getId()) + arquivo.getOriginalFilename()));
				produtoRepo.save(produto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/produtos";
	}

	@GetMapping("/produto/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto==null) {
			throw new IllegalArgumentException("Produto inválido!");
		}

		Produto produtoBanco = produto.get();
		if (produtoBanco.isStatus()) {
			produtoBanco.setStatus(false);
		} else {
			produtoBanco.setStatus(true);
		}
		produtoRepo.save(produtoBanco);
		return "redirect:/produtos";
	}

	@GetMapping("/produto/detalhes/{id}")
	public String detalhesProduto(@PathVariable("id") long id, Model model) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto==null) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		model.addAttribute("produto", produto.get());
		model.addAttribute("imagens", produtoImagensRepo.findAll());
		return "backoffice/detalhesProduto";
	}

	@GetMapping("/produto/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		System.out.println(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());

		}
		return null;
	}
}
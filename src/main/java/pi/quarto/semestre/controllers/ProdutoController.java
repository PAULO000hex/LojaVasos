package pi.quarto.semestre.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.models.ProdutoImagens;
import pi.quarto.semestre.repositories.ProdutoImagensRepositorio;
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class ProdutoController {
	@Autowired
	private ProdutoImagensRepositorio produtoImagensRepo;
	private static String caminhoImagens = "C:\\Users\\wmdbox\\Downloads\\imagensPI\\";
	private ProdutoRepositorio produtoRepo;

	public ProdutoController(ProdutoRepositorio produtoRepo) {
		this.produtoRepo = produtoRepo;
	}

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("listaProdutos", produtoRepo.findAll());
		return "index";
	}

	@GetMapping("/produtos")
	public String produtos(Model model) {
		model.addAttribute("listaProdutos",
				produtoRepo.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"))));
		return "produtos";
	}
	
	@GetMapping("/produtospag")
	public ModelAndView carregaProdutosPaginacao(@PageableDefault(size = 5)Pageable pageable, ModelAndView model) {
		Page<Produto> pageProduto = produtoRepo.findAll(pageable);
		model.addObject("listaProdutos", pageProduto);
		model.setViewName("/produtos");
		return model;
	}
	
	@GetMapping("/produto/imagens/{id}")
	public String produtoImagens(@PathVariable("id") long id, Model model) {
		Optional<Produto> produtoOpt = produtoRepo.findById(id);
		if (produtoOpt.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}

		model.addAttribute("produto", produtoOpt.get());
		return "formImagens";
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
				
				ProdutoImagens prodImg = new ProdutoImagens();
				prodImg.setUrl(String.valueOf((produto.getId()) + arquivo.getOriginalFilename()));
				prodImg.setProduto(produto);
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
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult bindingResult,
			@RequestParam("file") MultipartFile arquivo) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "formProduto";
		}

		produto.setStatus(true);
		produtoRepo.save(produto);
		try {
			if (!arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths
						.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				produto.setImage_url(String.valueOf((produto.getId()) + arquivo.getOriginalFilename()));
				produtoRepo.save(produto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/produtospag";
	}

	@GetMapping("/produto/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto.isEmpty()) {
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
		if (produto.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		model.addAttribute("produto", produto.get());
		model.addAttribute("imagens", produtoImagensRepo.findAll());
		return "detalhesProduto";
	}

	@GetMapping("/produto/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());

		}
		return null;
	}
}
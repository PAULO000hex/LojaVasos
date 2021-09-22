package pi.quarto.semestre.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

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
import pi.quarto.semestre.repositories.ProdutoRepositorio;

@Controller
public class ProdutoController {
        private static String caminhoImagens = "C:\\Users\\AmandaDosSantosBetti\\Documents\\imagens\\";
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
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult bindingResult, @RequestParam("file") MultipartFile arquivo) {		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "formProduto";
		}
		
		produto.setStatus(true);
		produtoRepo.save(produto);
                try{
                    if(!arquivo.isEmpty()){
                        byte[] bytes = arquivo.getBytes();
                        Path caminho = Paths.get(caminhoImagens+String.valueOf(produto.getProduct_id())+arquivo.getOriginalFilename());
                        Files.write(caminho, bytes);
                        produto.setImage_url(String.valueOf((produto.getProduct_id())+arquivo.getOriginalFilename()));
                        produtoRepo.save(produto);
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
		return "redirect:/produtos";
	}
	
	@GetMapping("/produto/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		produtoRepo.delete(produto.get());
		return "redirect:/produtos";
	}
	
	@GetMapping("/produto/detalhes/{id}")
	public String detalhesProduto(@PathVariable("id") long id, Model model) {
		Optional<Produto> produto = produtoRepo.findById(id);
		if (produto.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido!");
		}
		
		model.addAttribute("produto", produto.get());
		return "detalhesProduto";
	}
        
        @GetMapping("/produto/mostrarImagem/{imagem}")
	@ResponseBody
        public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens+imagem);
                if(imagem!=null || imagem.trim().length()>0){
                return Files.readAllBytes(imagemArquivo.toPath());
            
	}
                return null;
}
}
package pi.quarto.semestre.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.models.Compra;
import pi.quarto.semestre.models.Endereco;
import pi.quarto.semestre.models.ItensCompra;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.EnderecoRepository;
import pi.quarto.semestre.repositories.ProdutoRepositorio;



@Controller
public class CarrinhoController {
	
	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();
	
	@Autowired
	private ProdutoRepositorio prodRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	private void calcularTotal() {
		compra.setValorTotal(0.0);
		for(ItensCompra it: itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
		
	}
	
	@GetMapping("/carrinho")
	public ModelAndView carrinho(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("carrinho");
		calcularTotal();
		mv.addObject("compra",compra);
		if(request.getSession().getAttribute("id")!=null) {
		compra.setCepCliente(enderecoRepo.findEnderecoPrincipal((long)request.getSession().getAttribute("id")));
		}
		mv.addObject("listaItens", itensCompra);
		return mv;
		
	}
	
	@GetMapping("/finalizarCompra")
	public ModelAndView finalizarCompra(HttpServletRequest request) {
		if(request.getSession().getAttribute("id")==null) {
			ModelAndView mv = new ModelAndView("redirect:/loginCliente");
			return mv;
		}
		ModelAndView mv = new ModelAndView("finalizarCompra");
		calcularTotal();
		mv.addObject("compra",compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
		
	}
	
	/*@GetMapping("selecaoEndereco")
	public ModelAndView selecaoEndereco(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("selecaoEndereco");
		calcularTotal();
		if(request.getSession().getAttribute("id")!=null) {
		List<Endereco> endereco = enderecoRepo.findEnderecoEntrega((long)request.getSession().getAttribute("id"));
		mv.addObject("listaEnderecos", endereco);
		}else if(request.getSession().getAttribute("id")==null) {
				mv.setViewName("redirect:/loginCliente");
			return mv;
		}
		mv.addObject("compra",compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
		
	}
	*/
	@GetMapping("/alterarQuantidade/{produtoid}/{acao}")
	public String alterarQuantidade(@PathVariable Long produtoid, @PathVariable Integer acao) {
		
		
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(produtoid)) {
				if(acao.equals(1) && it.getQuantidade()<it.getProduto().getAvailable()) {
				it.setQuantidade(it.getQuantidade()+1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal()+ (it.getQuantidade()*it.getValorUnitario()));
				}else if(acao==0 && it.getQuantidade()>1) {
					it.setQuantidade(it.getQuantidade()-1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal()+ (it.getQuantidade()*it.getValorUnitario()));
				}
				break;
			}
		}
		
		return "redirect:/carrinho";
		
	}
	
	@GetMapping("/removerProduto/{produtoid}")
	public String removerProdutoCarrinho(@PathVariable Long produtoid) {
		
		
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(produtoid)) {
				itensCompra.remove(it);
				break;
			}
		}
		
		return "redirect:/carrinho";
		
	}
	
	@GetMapping("/adicionarCarrinho/{produtoid}")
	public String adicionarCarrinho(@PathVariable Long produtoid) {
		Optional<Produto> prod = prodRepo.findById(produtoid);
		Produto produto = prod.get();
		
		int controle = 0;
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade()+1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal()+ (it.getQuantidade()*it.getValorUnitario()));
				controle = 1;
				break;
			}
		}
		if(controle == 0) {
		ItensCompra item = new ItensCompra();
		item.setProduto(produto);
		item.setValorUnitario(produto.getPrice());
		item.setQuantidade(item.getQuantidade() + 1);
		item.setValorTotal(item.getValorTotal()+ (item.getQuantidade()*item.getValorUnitario()));
		itensCompra.add(item);
		}
		
		return "redirect:/carrinho";
		
	
}
	
}
package pi.quarto.semestre.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.models.Compra;
import pi.quarto.semestre.models.ItensCompra;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.ProdutoRepositorio;



@Controller
public class CarrinhoController {
	
	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();
	
	@Autowired
	private ProdutoRepositorio prodRepo;
	
	private void calcularTotal() {
		compra.setValorTotal(0.0);
		for(ItensCompra it: itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}
	
	@GetMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		calcularTotal();
		mv.addObject("compra",compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
		
	}
	
	@GetMapping("/alterarQuantidade/{produtoid}/{acao}")
	public String alterarQuantidade(@PathVariable Long produtoid, @PathVariable Integer acao) {
		
		
		for(ItensCompra it:itensCompra) {
			if(it.getProduto().getId().equals(produtoid)) {
				if(acao.equals(1)) {
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
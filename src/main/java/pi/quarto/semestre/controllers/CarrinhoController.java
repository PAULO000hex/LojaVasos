package pi.quarto.semestre.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.models.*;
import pi.quarto.semestre.repositories.ClienteRepository;
import pi.quarto.semestre.repositories.EnderecoRepository;
import pi.quarto.semestre.repositories.PedidoRepository;
import pi.quarto.semestre.repositories.ProdutoRepositorio;



@Controller
public class CarrinhoController {
	
	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();
	Pedido pedido = new Pedido();
	
	@Autowired
	private ProdutoRepositorio prodRepo;

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
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
	
	@GetMapping("/DetalhesPedido")
	public ModelAndView detalhesPedido(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("DetalhesPedido");
		mv.addObject("compra",compra);
		mv.addObject("listaItens", itensCompra);

		for(ItensCompra it:itensCompra) {
			pedido.setQuantidade(it.getQuantidade());
			pedido.setProduto(it.getProduto());

			}

		Cliente cliente = clienteRepo.findUsuarioById((long)request.getSession().getAttribute("id"));

		pedido.setStatus("aguardando pagamento");
		pedido.setValor(compra.getValorTotal());
		pedido.setCliente(cliente);
		pedido.setIdCliente((long)request.getSession().getAttribute("id"));
		Pedido pedido2 = new Pedido();
		pedido2 = pedido;
		
		pedidoRepo.save(pedido2);
		List<Pedido>pedido3 = pedidoRepo.findPedidoById((long)request.getSession().getAttribute("id"));
		mv.addObject("listaPedidos",pedido3);
		return mv;
		
	}
	
	@GetMapping("/finalizarCompra")
	public ModelAndView finalizarCompra(HttpServletRequest request, @RequestParam ("frete") float frete, @RequestParam ("endereco") String endereco) {
		if(request.getSession().getAttribute("id")==null) {
			ModelAndView mv = new ModelAndView("redirect:/loginCliente");
			return mv;
		}
		System.out.println(endereco);
		ModelAndView mv = new ModelAndView("finalizarCompra");
		calcularTotal();
		
		mv.addObject("compra",compra);
		compra.setValorTotal(compra.getValorTotal()+frete);
		compra.setFrete(frete);
		mv.addObject("listaItens", itensCompra);
		
		return mv;
		
	}
	
	@GetMapping("selecaoEndereco")
	public ModelAndView selecaoEndereco(HttpServletRequest request, @RequestParam ("categoria")String categoria) {
		ModelAndView mv = new ModelAndView("selecaoEndereco");
		calcularTotal();
		if(request.getSession().getAttribute("id")!=null) {
		List<Endereco> endereco = enderecoRepo.findEnderecoEntrega((long)request.getSession().getAttribute("id"));
		mv.addObject("listaEnderecos", endereco);
		mv.addObject("categoria",categoria);
		}else if(request.getSession().getAttribute("id")==null) {
				mv.setViewName("redirect:/loginCliente");
			return mv;
		}
		mv.addObject("compra",compra);
		mv.addObject("listaItens", itensCompra);
		return mv;
		
	}
	
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
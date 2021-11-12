package pi.quarto.semestre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrinhoController {
	
	@GetMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv = new ModelAndView("carrinho");
		return mv;
		
	}
	
	@GetMapping("/adicionarCarrinho/{produtoid}")
	public ModelAndView adicionarCarrinho(@PathVariable Long produtoid) {
		ModelAndView mv = new ModelAndView("carrinho");
		return mv;
		
	
}
	
}
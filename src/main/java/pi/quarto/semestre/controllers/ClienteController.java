package pi.quarto.semestre.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.service.ServiceUsuario;

import pi.quarto.semestre.models.Cliente;
import pi.quarto.semestre.models.Endereco;
import pi.quarto.semestre.models.Funcionario;
import pi.quarto.semestre.repositories.ClienteRepository;
import pi.quarto.semestre.repositories.EnderecoRepository;

@Controller
public class ClienteController {

	
	@Autowired
	ServiceUsuario serviceUsuario;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrocliente")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocliente"); // prepara o retorno do ModelAndv
		modelAndView.addObject("clienteobj", new Cliente()); // objeto vazio
		return modelAndView;
	}
	
	

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarcliente")
	public ModelAndView salvar(Cliente cliente) throws Exception {
		cliente.setStatus(true);
		if (cliente.getId() != 0) {
			serviceUsuario.editarUsuarioc(cliente);
		} else {
			serviceUsuario.salvarUsuarioc(cliente);// salva
		}

		ModelAndView andView = new ModelAndView("redirect:/cadastro/cadastroEnderecoEntrega/" + cliente.getId());
		

		return andView;
		
	}
	
	
	@GetMapping("/cadastro/cadastroEnderecoEntrega/{clienteid}")
	public ModelAndView enderecos(@PathVariable("clienteid") long clienteid) {// Intercepta url passando idpesoa {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroEnderecoEntrega");
		modelAndView.addObject("enderecoobj", new Endereco()); // objeto vazio
		modelAndView.addObject("clienteid", clienteid);
		
		return modelAndView;
	}
	
	
	/// ADCIONA NOVO ENDERECO
	@RequestMapping(method = RequestMethod.POST, value = "**/adicionarendereco/")
	public ModelAndView adcEndereco(Endereco endereco ){
        System.out.println(endereco.getCliente());
		enderecoRepository.save(endereco);
		ModelAndView modelAndView = new ModelAndView("redirect:/cadastro/cadastroEnderecoEntrega/"+endereco.getClienteid());
		return modelAndView ;
	}
	
	
	
	
}

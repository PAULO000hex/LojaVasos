package pi.quarto.semestre.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.service.ServiceUsuario;
import pi.quarto.semestre.util.Util;
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

	@GetMapping("/loginCliente")
	public ModelAndView logincliente() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/loginCliente");
		return mv;
	}

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

		ModelAndView andView = new ModelAndView("redirect:/cadastro/cadastroEnderecoFaturamento/" + cliente.getId());
		

		return andView;
		
	}
	
	@GetMapping("/cadastro/cadastroEnderecoFaturamento/{clienteid}")
	public ModelAndView endereco1(@PathVariable("clienteid") long clienteid, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroEnderecoFaturamento");
		modelAndView.addObject("enderecoobj", new Endereco()); // objeto vazio
		modelAndView.addObject("clienteid", clienteid);
		modelAndView.addObject("id",request.getSession().getAttribute("id"));

		return modelAndView;
	}
	
	
	@GetMapping("/cadastro/cadastroEnderecoEntrega/{clienteid}")
	public ModelAndView enderecos(@PathVariable("clienteid") long clienteid, HttpServletRequest request) {// Intercepta url passando idpesoa {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroEnderecoEntrega");
		modelAndView.addObject("enderecoobj", new Endereco()); // objeto vazio
		modelAndView.addObject("clienteid", clienteid);
		modelAndView.addObject("id",request.getSession().getAttribute("id"));

		return modelAndView;
	}
	
	
	/// ADCIONA NOVO ENDERECO
	@RequestMapping(method = RequestMethod.POST, value = "**/adicionarendereco/")
	public ModelAndView adcEndereco(Endereco endereco ){
        
		enderecoRepository.save(endereco);
		ModelAndView modelAndView = new ModelAndView("redirect:/cadastro/cadastroEnderecoEntrega/"+endereco.getClienteid());
		return modelAndView ;
	}
	
	@PostMapping("/loginCliente")
    public String loginUsuario(Model model, Cliente cliente, HttpServletRequest request) throws NoSuchAlgorithmException {
    	Cliente user = this.clienteRepository.Login(cliente.getEmail(), Util.md5(cliente.getSenha()));
    	if(user != null) {
    		request.getSession().setAttribute("nome", user.getNome());
    		request.getSession().setAttribute("id", user.getId());
    		
    		return "redirect:/cliente"; 
    	}
    	model.addAttribute("erro", "Usuário ou senha invalidos");
	    return "/loginCliente";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cliente")
	public ModelAndView pessoa(HttpServletRequest request) {
		ModelAndView andView = new ModelAndView("/cliente");
		List<Endereco> enderecos = enderecoRepository.findEnderecoById((long)request.getSession().getAttribute("id"));
		Cliente cliente = clienteRepository.findUsuarioById((long)request.getSession().getAttribute("id"));
		andView.addObject("cliente", cliente);
		andView.addObject("listaEnderecos", enderecos);
		return andView;

	}
	
	@GetMapping("/editarcliente/{clienteid}")
	public ModelAndView editar(@PathVariable("clienteid") long clienteid) {// Intercepta url passando idpesoa {

		Optional<Cliente> pessoa = clienteRepository.findById(clienteid);// carrega objeto do banco e consulta

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocliente"); // prepara o retorno do mav
		modelAndView.addObject("clienteobj", pessoa.get()); // passa objeto para edicao

		return modelAndView;
	}

	@GetMapping("/editarprincipal/{enderecoid}")
	public ModelAndView editarPrincipal(@PathVariable("enderecoid") long enderecoid) {// Intercepta url passando idpesoa {

		Iterable<Endereco> enderecos = enderecoRepository.findAll();

		for( Endereco endereco: enderecos){
			if(endereco.isPrincipal()){
				endereco.setPrincipal(false);
			}
		}

		Optional<Endereco> endereco = enderecoRepository.findById(enderecoid);

		Endereco enderecobanco = endereco.get();

		enderecobanco.setPrincipal(true);

		enderecoRepository.save(enderecobanco);

		ModelAndView modelAndView = new ModelAndView("redirect:/cliente");

		return modelAndView;
	}

	@GetMapping("/removerendereco/{idendereco}")
	public ModelAndView excluir(@PathVariable("idendereco") Long idendereco) {

		Optional<Endereco> endereco = enderecoRepository.findById(idendereco);

		if (endereco == null) {
			throw new IllegalArgumentException("endereco inválido!");
		}

		Endereco enderecobanco = endereco.get();

		enderecobanco.setStatus(false);

		enderecoRepository.save(enderecobanco);

		ModelAndView modelAndView = new ModelAndView("redirect:/cliente");

		return modelAndView;
	}
	
}

package pi.quarto.semestre.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.management.AttributeValueExp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pi.quarto.semestre.Exceptions.ServiceExc;
import pi.quarto.semestre.models.Funcionario;
import pi.quarto.semestre.repositories.FuncionarioRepository;
import pi.quarto.semestre.service.ServiceUsuario;
import pi.quarto.semestre.util.Util;

@Controller
public class FuncionarioController {

	

		
		@Autowired
		private ServiceUsuario serviceUsuario;
		
		@Autowired
		private FuncionarioRepository funcionarioRepository;


		
		@GetMapping("/login")
		public ModelAndView login() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/login");
			return mv;
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
		public ModelAndView inicio() {

			ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa"); // prepara o retorno do ModelAndv
			modelAndView.addObject("pessoaobj", new Funcionario()); // objeto vazio
			return modelAndView;
		}

		@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
		public ModelAndView salvar(Funcionario pessoa) throws Exception {
			pessoa.setStatus(true);
			if(pessoa.getId() != 0) {
			serviceUsuario.editarUsuario(pessoa);
			}else{
				serviceUsuario.salvarUsuario(pessoa);// salva
			}

			ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
			Iterable<Funcionario> pessoasIt = funcionarioRepository.findAll();// consulta
			andView.addObject("pessoas", pessoasIt); // carrega lista de usuarios
			andView.setViewName("redirect:/cadastro/listarpessoas");

			andView.addObject("pessoaobj", new Funcionario());// objeto vazio para o formulario
			return andView;

		}

		@RequestMapping(method = RequestMethod.GET, value = "cadastro/listarpessoas")
		public ModelAndView pessoas(HttpServletRequest request) {
			
			if(request.getSession().getAttribute("admin") == null || (boolean) request.getSession().getAttribute("admin") == false) {
				return new ModelAndView("/login");
			}
			
			ModelAndView andView = new ModelAndView("cadastro/listarpessoas");
			Iterable<Funcionario> pessoasIt = funcionarioRepository.findAll();
			andView.addObject("userId",request.getSession().getAttribute("id"));
			andView.addObject("pessoas", pessoasIt);
			andView.addObject("pessoaobj", new Funcionario());
			return andView;

		}
		
		@RequestMapping(method = RequestMethod.GET, value = "cadastro/listarpessoa")
		public ModelAndView pessoa(HttpServletRequest request) {
			

			ModelAndView andView = new ModelAndView("cadastro/listarpessoa");
			Funcionario pessoasIt = funcionarioRepository.findUsuarioById((long)request.getSession().getAttribute("id"));
			System.out.println("id " + pessoasIt.getId());
			andView.addObject("pessoas", pessoasIt);
			andView.addObject("pessoaobj", new Funcionario());
			return andView;

		}
		
		

		@GetMapping("/editarpessoa/{idpessoa}")
		public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {// Intercepta url passando idpesoa {

			Optional<Funcionario> pessoa = funcionarioRepository.findById(idpessoa);// carrega objeto do banco e consulta

			ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa"); // prepara o retorno do mav
			modelAndView.addObject("pessoaobj", pessoa.get()); // passa objeto para edicao

			return modelAndView;
		}

		
		
		
		@GetMapping("/removerpessoa/{idpessoa}")
		public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {// Intercepta url passando idpesoa {

			Optional<Funcionario> pessoa = funcionarioRepository.findById(idpessoa);// carrega objeto do banco e consulta

			if (pessoa == null) {
				throw new IllegalArgumentException("pessoa inválido!");
			}

			Funcionario pessoabanco = pessoa.get();
			if (pessoabanco.isStatus()) {
				pessoabanco.setStatus(false);
			} else {
				pessoabanco.setStatus(true);
			}

			funcionarioRepository.save(pessoabanco);
			ModelAndView modelAndView = new ModelAndView("redirect:/cadastro/listarpessoas"); // prepara o retorno do mav
			modelAndView.addObject("pessoaobj", new Funcionario()); // passa objeto para edicao

			return modelAndView;
		}
		
		@GetMapping("/alterargrupo/{idpessoa}")
		public ModelAndView alterarGrupo(@PathVariable("idpessoa") Long idpessoa) {// Intercepta url passando idpesoa {

			Optional<Funcionario> pessoa = funcionarioRepository.findById(idpessoa);// carrega objeto do banco e consulta

			if (pessoa == null) {
				throw new IllegalArgumentException("pessoa inválido!");
			}

			Funcionario pessoabanco = pessoa.get();
			if (pessoabanco.isAdmin()) {
				pessoabanco.setAdmin(false);
			} else {
				pessoabanco.setAdmin(true);
			}

			funcionarioRepository.save(pessoabanco);
			ModelAndView modelAndView = new ModelAndView("redirect:/cadastro/listarpessoas"); // prepara o retorno do mav
			modelAndView.addObject("pessoaobj", new Funcionario()); // passa objeto para edicao

			return modelAndView;
		}
		
		
		@PostMapping("/login")
	    public String loginUsuario(Model model, Funcionario usuario, HttpServletRequest request) throws NoSuchAlgorithmException {
	    	Funcionario user = this.funcionarioRepository.Login(usuario.getEmail(), Util.md5(usuario.getSenha()));
	    	if(user != null) {
	    		request.getSession().setAttribute("nome", user.getNome());
	    		request.getSession().setAttribute("id", user.getId());
	    		request.getSession().setAttribute("admin",user.isAdmin());
	    		
	    		return "redirect:/menu"; 
	    	}
	    	model.addAttribute("erro", "Usuário ou senha invalidos");
		    return "/login";
		}
	}
	
	
	
	
	
	


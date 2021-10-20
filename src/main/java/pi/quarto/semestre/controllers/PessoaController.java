package pi.quarto.semestre.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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
import pi.quarto.semestre.models.Pessoa;
import pi.quarto.semestre.models.Produto;
import pi.quarto.semestre.repositories.PessoaRepository;
import pi.quarto.semestre.repositories.ProdutoRepositorio;
import pi.quarto.semestre.service.ServiceUsuario;
import pi.quarto.semestre.util.Util;

@Controller
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@Autowired
	private ProdutoRepositorio produtoRepo;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/login");
		return mv;
	}
	


	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa"); // prepara o retorno do ModelAndv
		modelAndView.addObject("pessoaobj", new Pessoa()); // objeto vazio
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) throws Exception {
		pessoa.setStatus(true);
		System.out.println("oie "+pessoa.getSenha());
		serviceUsuario.salvarUsuario(pessoa);// salva

		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();// consulta
		andView.addObject("pessoas", pessoasIt); // carrega lista de usuarios

		andView.addObject("pessoaobj", new Pessoa());// objeto vazio para o formulario
		return andView;

	}

	@RequestMapping(method = RequestMethod.GET, value = "cadastro/listarpessoas")
	public ModelAndView pessoas() {

		ModelAndView andView = new ModelAndView("cadastro/listarpessoas");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;

	}

	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {// Intercepta url passando idpesoa {

		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);// carrega objeto do banco e consulta

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa"); // prepara o retorno do mav
		modelAndView.addObject("pessoaobj", pessoa.get()); // passa objeto para edicao

		return modelAndView;
	}

	@GetMapping("/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {// Intercepta url passando idpesoa {

		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);// carrega objeto do banco e consulta

		if (pessoa == null) {
			throw new IllegalArgumentException("pessoa inválido!");
		}

		Pessoa pessoabanco = pessoa.get();
		if (pessoabanco.isStatus()) {
			pessoabanco.setStatus(false);
		} else {
			pessoabanco.setStatus(true);
		}

		pessoaRepository.save(pessoabanco);
		ModelAndView modelAndView = new ModelAndView("redirect:/cadastro/listarpessoas"); // prepara o retorno do mav
		modelAndView.addObject("pessoaobj", new Pessoa()); // passa objeto para edicao

		return modelAndView;
	}
	@PostMapping("/login")
    public String loginUsuario(Model model, Pessoa usuario, HttpServletRequest request) throws NoSuchAlgorithmException {
    	Pessoa user = this.pessoaRepository.Login(usuario.getEmail(), Util.md5(usuario.getSenha()));
    	if(user != null) {
    		request.getSession().setAttribute("nome", user.getNome());
    		request.getSession().setAttribute("id", user.getId());
    		request.getSession().setAttribute("admin",user.isAdmin());
    		System.out.println(request.getSession().getAttribute("usuario"));
    		
    		return "redirect:/"; 
    	}
    	model.addAttribute("erro", "Usuário ou senha invalidos");
	    return "/login";
	}
}

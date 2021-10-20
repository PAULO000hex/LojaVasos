package pi.quarto.semestre.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pi.quarto.semestre.Exceptions.CriptoExistException;
import pi.quarto.semestre.Exceptions.EmailExistsException;
import pi.quarto.semestre.Exceptions.ServiceExc;
import pi.quarto.semestre.models.Pessoa;
import pi.quarto.semestre.repositories.PessoaRepository;
import pi.quarto.semestre.util.*;

@Service
public class ServiceUsuario {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public void salvarUsuario(Pessoa user) throws Exception {
		
		try {
			if(pessoaRepository.findByEmail(user.getEmail()) != null) {
				throw new EmailExistsException("Já existe um email cadastrado para: " + user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
		}catch(NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		pessoaRepository.save(user);
	}
public void editarUsuario(Pessoa user) throws Exception {
		
		try {
			if(pessoaRepository.findByEmail(user.getEmail()) == null) {
				throw new EmailExistsException("Não existe um email cadastrado para: " + user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
		}catch(NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		pessoaRepository.save(user);
	}
	
}

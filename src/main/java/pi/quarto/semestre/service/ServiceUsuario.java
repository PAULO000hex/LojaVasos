package pi.quarto.semestre.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pi.quarto.semestre.Exceptions.CriptoExistException;
import pi.quarto.semestre.Exceptions.EmailExistsException;
import pi.quarto.semestre.models.Funcionario;
import pi.quarto.semestre.repositories.FuncionarioRepository;
import pi.quarto.semestre.util.*;

@Service
public class ServiceUsuario {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void salvarUsuario(Funcionario user) throws Exception {
		
		try {
			if(funcionarioRepository.findByEmail(user.getEmail()) != null) {
				throw new EmailExistsException("Já existe um email cadastrado para: " + user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
		}catch(NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		funcionarioRepository.save(user);
	}
public void editarUsuario(Funcionario user) throws Exception {
		
		try {
			if(funcionarioRepository.findByEmail(user.getEmail()) == null) {
				throw new EmailExistsException("Não existe um email cadastrado para: " + user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
		}catch(NoSuchAlgorithmException e) {
			throw new CriptoExistException("Erro na criptografia da senha");
		}
		funcionarioRepository.save(user);
	}
	
}

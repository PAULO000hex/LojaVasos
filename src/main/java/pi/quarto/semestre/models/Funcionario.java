package pi.quarto.semestre.models;

import javax.persistence.Entity;

@Entity
public class Funcionario extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private boolean backoffice;
	private boolean admin;
	
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}
	
	public Funcionario( String nome, String email, String cpf, String senha, boolean status,  boolean backoffice, boolean admin ) {
		this.nome = nome;
        this.email= email;
		this.cpf = cpf;
		this.senha = senha;
		this.status = status;
		this.backoffice = backoffice;
		this.admin=admin;
		
	} 
	


	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isBackoffice() {
		return backoffice;
	}

	public void setBackoffice(boolean backoffice) {
		this.backoffice = backoffice;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	
}

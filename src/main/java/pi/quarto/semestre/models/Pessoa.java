package pi.quarto.semestre.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String nome;
	private boolean status;
	@Column(unique=true)
	private String email;
	private String cpf;
	private String senha;
	private boolean admin;
	private boolean backoffice;
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}
	
	public Pessoa( String nome, String email, String cpf, String senha, boolean status, boolean backoffice,
			boolean admin) {
		this.nome = nome;
        this.email= email;
		this.cpf = cpf;
		this.senha = senha;
		this.status = status;
		this.backoffice = backoffice;
		this.admin = admin;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isBackoffice() {
		return backoffice;
	}
	public void setBackoffice(boolean backoffice) {
		this.backoffice = backoffice;
	}
	
}

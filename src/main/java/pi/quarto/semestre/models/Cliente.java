
  package pi.quarto.semestre.models;
  
  
  import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
  
  @Entity
  public class Cliente extends Pessoa {
 
 
  
  private static final long serialVersionUID = 1L;
  
  

  
  
  @OneToMany(mappedBy="cliente") // substituir por cliente private
  List<Endereco> enderecos;
  
  
  
  
 public List<Endereco> getEnderecos() { return enderecos; }
  
  public void setEnderecos(List<Endereco> enderecos) { this.enderecos =
 enderecos; }
  
  
  


public static long getSerialversionuid() {
	return serialVersionUID;
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
  }


  package pi.quarto.semestre.models;
  
  
  import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
  
  @Entity
  public class Cliente extends Pessoa {
 
 
  
  private static final long serialVersionUID = 1L;
  
  

  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date nascimento;


	  public String getGenero() {
		  return genero;
	  }

	  public void setGenero(String genero) {
		  this.genero = genero;
	  }

	  private String genero;
  
  
  public Cliente() {

}
  
  public Cliente( String nome, String email, String cpf, String senha, boolean status, Date nascimento   ) {
		this.nome = nome;
      this.email= email;
		this.cpf = cpf;
		this.senha = senha;
		this.status = status;
		this.nascimento=nascimento;
		
	} 


@OneToMany(mappedBy="cliente") // substituir por cliente private
  List<Endereco> enderecos;
  

public List<Endereco> getEnderecos() {
	return enderecos;
}

public void setEnderecos(List<Endereco> enderecos) {
	this.enderecos = enderecos;
}

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

	public Date getNascimento() {
		return nascimento;
	}


	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}


  }

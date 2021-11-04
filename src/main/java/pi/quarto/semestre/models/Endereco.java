package pi.quarto.semestre.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@SuppressWarnings("deprecation")
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	private String logradouro;
	private String cep;
	private String bairro;
	private String cidade;
	private String uf;
	private String numero;
	private long clienteid;
	private boolean faturamento;
	private boolean entrega;
	@Column(columnDefinition = "boolean default true") 
	private boolean status;
	
	/*@ManyToOne()
	@ForeignKey(name= "cliente_id")
	private Cliente cliente;*/
	
	private Cliente cliente;
	
	public long getClienteid() {
		return clienteid;
	}

	public void setClienteid(long clienteid) {
		this.clienteid = clienteid;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public boolean isFaturamento() {
		return faturamento;
	}

	public void setFaturamento(boolean faturamento) {
		this.faturamento = faturamento;
	}

	public boolean isEntrega() {
		return entrega;
	}

	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	



	
}

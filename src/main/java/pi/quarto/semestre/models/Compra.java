package pi.quarto.semestre.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Compra implements Serializable {

	/**
	 * 
	 */

	public Compra() {
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@ManyToOne
	private Cliente cliente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompra = new Date();
	private float frete;
	private String cepCliente;
	private String emailPaypal;
	private String nomeCartao;
	private String numeroCartao;
	private String vencimentoCartao;
	private String cvvCartao;
	private String formaPagamento;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}


	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	public String getCepCliente() {
		return cepCliente;
	}

	private Double valorTotal = 0.;

	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
		
	}

	public float getFrete() {
		return frete;
	}

	public void setFrete(float frete) {
		this.frete = frete;
	}

	public String getEmailPaypal() {
		return emailPaypal;
	}

	public void setEmailPaypal(String emailPaypal) {
		this.emailPaypal = emailPaypal;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getVencimentoCartao() {
		return vencimentoCartao;
	}

	public void setVencimentoCartao(String vencimentoCartao2) {
		this.vencimentoCartao = vencimentoCartao2;
	}

	public String getCvvCartao() {
		return cvvCartao;
	}

	public void setCvvCartao(String cvvCartao) {
		this.cvvCartao = cvvCartao;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
}

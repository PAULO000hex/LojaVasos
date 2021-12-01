package pi.quarto.semestre.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
@Entity
public class Pedido {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long Id;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Cliente cliente;
	
	private String status;
	
	
	private Long idCliente;

	private ItensCompra itensCompra;
	
	private double valor;
	private int quantidade;

	
	public Pedido() {
	
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ItensCompra getItensCompra() {
		return itensCompra;
	}
	public void setItensCompra(ItensCompra itensCompra) {
		this.itensCompra = itensCompra;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}





}

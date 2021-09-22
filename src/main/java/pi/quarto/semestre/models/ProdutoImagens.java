package pi.quarto.semestre.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoImagens {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String url;
	
	@ManyToOne()
	@JoinColumn(name = "produto")
	private Produto produto;

	public ProdutoImagens() {}
	
	public ProdutoImagens(String url, Produto produto) {
		this.url = url;
		this.produto = produto;
	}

	public Long getImagemId() {
		return id;
	}

	public void setImagemId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
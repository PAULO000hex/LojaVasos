package pi.quarto.semestre.models;

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
	
	private boolean ismain;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "id")
	private Produto produto;

	public ProdutoImagens() {}
	
	public ProdutoImagens(Long id, String url, boolean ismain, Produto produto) {
		this.id = id;
		this.url = url;
		this.ismain = ismain;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isIsmain() {
		return ismain;
	}

	public void setIsmain(boolean ismain) {
		this.ismain = ismain;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
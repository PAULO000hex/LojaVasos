package pi.quarto.semestre.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	//@ManyToOne(cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "produto")
	private Long idProduto;

	public ProdutoImagens() {}
	
	public ProdutoImagens(String url, Long idProduto) {
		this.url = url;
		this.idProduto = idProduto;
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

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
}
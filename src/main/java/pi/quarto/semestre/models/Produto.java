package pi.quarto.semestre.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long product_id;
	
	@Size(max = 200, message = "O nome deve ter no máximo 200 caracteres!")
	@NotBlank(message = "O campo nome não pode estar vazio!")
	private String name;

	@Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres!")
	@NotBlank(message = "O campo descrição não pode estar vazio!")
	private String description;
	
	@NotBlank(message = "O campo tamanho não pode estar vazio!")
	private String size;

	@NotNull(message = "O campo preço não pode estar vazio!")
	private double price;

	@NotBlank(message = "O campo cor não pode estar vazio!")
	private String color;

	@NotNull(message = "O campo quantidade não pode estar vazio!")
	private int available;

	private boolean status;

	private String image_url;
	
	public Produto() {}

	public Produto(Long product_id, String name, String description, String size, double price, String color, int available,
			boolean status, String image_url) {
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.size = size;
		this.price = price;
		this.color = color;
		this.available = available;
		this.status = status;
		this.image_url = image_url;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(product_id, other.product_id);
	}

}
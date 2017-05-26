package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="product.all", query="SELECT p FROM Product p"),
	@NamedQuery(name="product.id", query="SELECT p FROM Product p WHERE p.id = :productId"),
	@NamedQuery(name="product.name", query="SELECT p FROM Product p WHERE p.name = :name"),
	@NamedQuery(name="product.category", query="SELECT p FROM Product p WHERE p.category = :category"),
	@NamedQuery(name="product.priceRange", query="SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
})

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Double price;
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}

package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Clothing")
public class Clothing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String name;

	@Column(length=100, nullable=true)
	private String image;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String gender;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String size;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String color;
	
	@Column(nullable=false)
	private float price;
	
	@Column(nullable=false)
	private int stock;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Mark mark;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Commerce commerce;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private ClothingType clothingType;

	@ManyToOne
	@JoinColumn(nullable=true)
	private Discount discount;
	
	public Clothing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clothing(int id,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String gender,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String size,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String color,
			String image, float price, int stock, boolean enabled, Mark mark, Commerce commerce, ClothingType clothingType,
			Discount discount) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.size = size;
		this.color = color;
		this.image = image;
		this.price = price;
		this.stock = stock;
		this.enabled = enabled;
		this.mark = mark;
		this.commerce = commerce;
		this.clothingType = clothingType;
		this.discount = discount;
	}

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public Commerce getCommerce() {
		return commerce;
	}

	public void setCommerce(Commerce commerce) {
		this.commerce = commerce;
	}

	public ClothingType getClothingType() {
		return clothingType;
	}

	public void setClothingType(ClothingType clothingType) {
		this.clothingType = clothingType;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	
}

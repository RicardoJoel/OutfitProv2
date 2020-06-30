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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Clothing")
public class Clothing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@javax.validation.constraints.Size(max=50, message="Máximo 50 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=50, nullable=false)
	private String name;

	@javax.validation.constraints.Size(max=500, message="Máximo 100 caracteres")
	@Column(length=100, nullable=true)
	private String image;
	
	@javax.validation.constraints.Size(max=10, message="Máximo 10 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=10, nullable=false)
	private String gender;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Size size;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Color color;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Mark mark;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Commerce commerce;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private ClothingType clothingType;

	@ManyToOne @JoinColumn(nullable=true)
	private Discount discount;
	
	@Min(value=0, message="El valor debe estar entre 0 y 1000")
	@Max(value=1000, message="El valor debe estar entre 0 y 1000")
	@Column(nullable=false)
	private float price;
	
	@Min(value=0, message="El valor debe estar entre 0 y 1000")
	@Max(value=1000, message="El valor debe estar entre 0 y 1000")
	@Column(nullable=false)
	private int stock;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public Clothing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clothing(int id,
			@javax.validation.constraints.Size(max = 50, message = "Máximo 50 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@javax.validation.constraints.Size(max = 100, message = "Máximo 100 caracteres") String image,
			@javax.validation.constraints.Size(max = 10, message = "Máximo 10 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String gender,
			@NotNull(message = "Debes elegir una opción") Size size,
			@NotNull(message = "Debes elegir una opción") Color color,
			@NotNull(message = "Debes elegir una opción") Mark mark,
			@NotNull(message = "Debes elegir una opción") Commerce commerce,
			@NotNull(message = "Debes elegir una opción") ClothingType clothingType, Discount discount,
			@Min(value = 0, message = "El valor debe estar entre 0 y 1000") @Max(value = 1000, message = "El valor debe estar entre 0 y 1000") float price,
			@Min(value = 0, message = "El valor debe estar entre 0 y 1000") @Max(value = 1000, message = "El valor debe estar entre 0 y 1000") int stock,
			boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.gender = gender;
		this.size = size;
		this.color = color;
		this.mark = mark;
		this.commerce = commerce;
		this.clothingType = clothingType;
		this.discount = discount;
		this.price = price;
		this.stock = stock;
		this.enabled = enabled;
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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

}

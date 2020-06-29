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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Size")
public class Size implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@javax.validation.constraints.Size(max=20, message="Máximo 20 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=20, nullable=false)
	private String name;

	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private ClothingType clothingType;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;

	public Size() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Size(int id,
			@javax.validation.constraints.Size(max = 20, message = "Máximo 20 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@NotNull(message = "Debes elegir una opción") ClothingType clothingType, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.clothingType = clothingType;
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

	public ClothingType getClothingType() {
		return clothingType;
	}

	public void setClothingType(ClothingType clothingType) {
		this.clothingType = clothingType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

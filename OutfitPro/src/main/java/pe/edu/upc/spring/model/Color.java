package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Color")
public class Color implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max=20, message="Máximo 20 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=20, nullable=false)
	private String name;

	@Size(max=10, message="Máximo 10 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=10, nullable=true)
	private String hexadecimal;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;

	public Color() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Color(int id,
			@Size(max = 20, message = "Máximo 20 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@Size(max = 10, message = "Máximo 10 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String hexadecimal,
			boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.hexadecimal = hexadecimal;
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

	public String getHexadecimal() {
		return hexadecimal;
	}

	public void setHexadecimal(String hexadecimal) {
		this.hexadecimal = hexadecimal;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}

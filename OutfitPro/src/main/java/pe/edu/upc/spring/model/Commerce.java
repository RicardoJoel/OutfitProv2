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
@Table(name="Commerce")
public class Commerce implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max=50, message="Máximo 50 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=50, nullable=false)
	private String name;

	@Size(max=11, message="Máximo 11 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=11, nullable=false)
	private String ruc;
	
	@Size(max=20, message="Máximo 20 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=20, nullable=false)
	private String email;
	
	@Size(max=50, message="Máximo 50 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=50, nullable=false)
	private String telephone;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public Commerce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commerce(int id,
			@Size(max = 50, message = "Máximo 50 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@Size(max = 11, message = "Máximo 11 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String ruc,
			@Size(max = 20, message = "Máximo 20 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String email,
			@Size(max = 50, message = "Máximo 50 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String telephone,
			boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.ruc = ruc;
		this.email = email;
		this.telephone = telephone;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

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

@Entity
@Table(name="Commerce")
public class Commerce implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String name;

	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String ruc;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String email;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String telephone;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public Commerce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commerce(int id,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String ruc,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String email,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String telephone,
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

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
@Table(name="Mark")
public class Mark implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String name;

	@Column(length=500, nullable=true)
	private String description;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;

	public Mark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mark(int id,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String description,
			boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}

package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="Assessor")
public class Assessor extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Size(max=500, message="Máximo 500 caracteres")
	@Column(length=500, nullable=true)
	private String reference;
	
	@Min(value=0, message="El valor debe estar entre 0 y 5")
	@Max(value=5, message="El valor debe estar entre 0 y 5")
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int calification;

	public Assessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Assessor(int id, 
					@Size(max = 20, message = "Máximo 20 caracteres") String username,
					@Size(max = 20, message = "Máximo 20 caracteres") String password,
					@Size(max = 50, message = "Máximo 50 caracteres") String name,
					@Size(max = 10, message = "Máximo 10 caracteres") String gender,
					@Size(max = 100, message = "Máximo 100 caracteres") String address, 
					boolean enabled, List<Role> roles,
					@Size(max = 500, message = "Máximo 500 caracteres") String reference,
					@Min(value = 0, message = "El valor debe estar entre 0 y 5") @Max(value = 5, message = "El valor debe estar entre 0 y 5") int calification) {
		super(id, username, password, name, gender, address, enabled, roles);
		// TODO Auto-generated constructor stub
		this.reference = reference;
		this.calification = calification;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
	}
	
}

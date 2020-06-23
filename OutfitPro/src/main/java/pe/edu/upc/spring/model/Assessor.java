package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Assessor")
public class Assessor extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(length=1000, nullable=true)
	private String reference;
	
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int calification;

	public Assessor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Assessor(int id, String username, String password, String name, String gender, String address, boolean enabled, List<Role> roles, String reference, int calification) {
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

package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Admin")
public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, 
			@Size(max = 50, message = "Máximo 50 caracteres") String username,
			@Size(max = 100, message = "Máximo 100 caracteres") String password,
			@Size(max = 50, message = "Máximo 50 caracteres") String name,
			@Size(max = 10, message = "Máximo 10 caracteres") String gender,
			@Size(max = 100, message = "Máximo 100 caracteres") String address, boolean enabled, List<Role> roles) {
		super(id, username, password, name, gender, address, enabled, roles);
		// TODO Auto-generated constructor stub
	}


}

package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String username, String password, String name, String gender, String address, Date birthdate,
			boolean enabled, List<Role> roles) {
		super(id, username, password, name, gender, address, birthdate, enabled, roles);
		// TODO Auto-generated constructor stub
	}

}

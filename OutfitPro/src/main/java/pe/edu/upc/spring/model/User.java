package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@Size(max=20, message="Máximo 20 caracteres")
	@Column(length=20, nullable=false, unique=true)
	private String username;
	
	@Size(max=20, message="Máximo 20 caracteres")
	@Column(length=20, nullable=false)
	private String password;
	
	@Size(max=50, message="Máximo 50 caracteres")
	@Column(length=50, nullable=true)
	private String name;
	
	@Size(max=10, message="Máximo 10 caracteres")
	@Column(length=10, nullable=true)
	private String gender;
	
	@Size(max=100, message="Máximo 100 caracteres")
	@Column(length=100, nullable=true)
	private String address;

	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Role> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, 
			@Size(max = 20, message = "Máximo 20 caracteres") String username,
			@Size(max = 20, message = "Máximo 20 caracteres") String password,
			@Size(max = 50, message = "Máximo 50 caracteres") String name,
			@Size(max = 10, message = "Máximo 10 caracteres") String gender,
			@Size(max = 100, message = "Máximo 100 caracteres") String address, boolean enabled, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.enabled = enabled;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

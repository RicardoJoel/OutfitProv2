package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Customer")
public class Customer extends User implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Column(nullable=false, columnDefinition = "boolean default false")
	private boolean premium = false;
	
	@Past(message="No puede seleccionar un día que no existe")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=true)
	private Date premActDate;

	@Past(message="No puede seleccionar un día que no existe")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=true)
	private Date premDesDate;

	@Min(value=0, message="El valor debe estar entre 0 y 5")
	@Max(value=5, message="El valor debe estar entre 0 y 5")
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int numFreeCons;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, 
			@Size(max = 50, message = "Máximo 50 caracteres") String username,
			@Size(max = 100, message = "Máximo 100 caracteres") String password,
			@Size(max = 50, message = "Máximo 50 caracteres") String name,
			@Size(max = 10, message = "Máximo 10 caracteres") String gender,
			@Size(max = 100, message = "Máximo 100 caracteres") String address, 
			boolean enabled, List<Role> roles,
			boolean premium, 
			@Past(message = "No puede seleccionar un día que no existe") Date premActDate,
			@Past(message = "No puede seleccionar un día que no existe") Date premDesDate,
			@Min(value = 0, message = "El valor debe estar entre 0 y 5") @Max(value = 5, message = "El valor debe estar entre 0 y 5") int numFreeCons) {
		super(id, username, password, name, gender, address, enabled, roles);
		// TODO Auto-generated constructor stub
		this.premium = premium;
		this.premActDate = premActDate;
		this.premDesDate = premDesDate;
		this.numFreeCons = numFreeCons;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public Date getPremActDate() {
		return premActDate;
	}

	public void setPremActDate(Date premActDate) {
		this.premActDate = premActDate;
	}

	public Date getPremDesDate() {
		return premDesDate;
	}

	public void setPremDesDate(Date premDesDate) {
		this.premDesDate = premDesDate;
	}

	public int getNumFreeCons() {
		return numFreeCons;
	}

	public void setNumFreeCons(int numFreeCons) {
		this.numFreeCons = numFreeCons;
	}

}

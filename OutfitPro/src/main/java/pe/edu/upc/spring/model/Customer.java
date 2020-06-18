package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

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

	@Column(nullable=false, columnDefinition = "integer default 0")
	private int numFreeCons;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String username, String password, String name, String gender, String address,
			Date birthdate, boolean enabled, List<Role> roles, boolean premium, Date premActDate, Date premDesDate, int numFreeCons) {
		super(id, username, password, name, gender, address, birthdate, enabled, roles);
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

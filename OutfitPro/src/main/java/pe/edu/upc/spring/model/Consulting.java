package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Consulting")
public class Consulting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne @JoinColumn(nullable=false)
	private Customer customer;
	
	@NotNull(message="Debes elegir un asesor")
	@ManyToOne @JoinColumn(nullable=false)
	private Assessor assessor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateTime;
	
	//@Min(value=0, message="El valor debe estar entre 0 y 5")
	//@Max(value=5, message="El valor debe estar entre 0 y 5")
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int calification;

	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public Consulting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulting(int id, Customer customer, @NotNull(message = "Debes elegir un asesor") Assessor assessor,
			Date dateTime, int calification, boolean enabled) {
		super();
		this.id = id;
		this.customer = customer;
		this.assessor = assessor;
		this.dateTime = dateTime;
		this.calification = calification;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Assessor getAssessor() {
		return assessor;
	}

	public void setAssessor(Assessor assessor) {
		this.assessor = assessor;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

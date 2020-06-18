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
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Consulting")
public class Consulting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="Campo obligatorio")
	@Past(message="No puede seleccionar un día que no existe")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=false)
	private Date premActDate;

	@Past(message="No puede seleccionar un día que no existe")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=true)
	private Date premDesDate;
	
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int calification;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Assessor assessor;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Customer customer;

	public Consulting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulting(int id,
			@NotNull(message = "Campo obligatorio") @Past(message = "No puede seleccionar un día que no existe") Date premActDate,
			@Past(message = "No puede seleccionar un día que no existe") Date premDesDate, int calification,
			boolean enabled, Assessor assessor, Customer customer) {
		super();
		this.id = id;
		this.premActDate = premActDate;
		this.premDesDate = premDesDate;
		this.calification = calification;
		this.enabled = enabled;
		this.assessor = assessor;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Assessor getAssessor() {
		return assessor;
	}

	public void setAssessor(Assessor assessor) {
		this.assessor = assessor;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}

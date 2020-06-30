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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	private Date initDate;

	@Past(message="No puede seleccionar un día que no existe")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=true)
	private Date endDate;
	
	@Min(value=0, message="El valor debe estar entre 0 y 5")
	@Max(value=5, message="El valor debe estar entre 0 y 5")
	@Column(nullable=false, columnDefinition = "integer default 0")
	private int calification;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Assessor assessor;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Customer customer;

	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public Consulting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulting(int id,
			@NotNull(message = "Campo obligatorio") @Past(message = "No puede seleccionar un día que no existe") Date initDate,
			@Past(message = "No puede seleccionar un día que no existe") Date endDate,
			@Min(value = 0, message = "El valor debe estar entre 0 y 5") @Max(value = 5, message = "El valor debe estar entre 0 y 5") int calification,
			@NotNull(message = "Debes elegir una opción") Assessor assessor,
			@NotNull(message = "Debes elegir una opción") Customer customer, boolean enabled) {
		super();
		this.id = id;
		this.initDate = initDate;
		this.endDate = endDate;
		this.calification = calification;
		this.assessor = assessor;
		this.customer = customer;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}

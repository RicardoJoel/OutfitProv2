package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Discount")
public class Discount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=100, nullable=false)
	private String name;

	@Column(length=500, nullable=true)
	private String description;
	
	@Column(nullable=false)
	private float amount;
	
	@Column(nullable=false)
	private float percentage;
	
	@NotNull(message="Campo obligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=false)
	private Date initDate;
	
	@NotNull(message="Campo obligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(nullable=false)
	private Date endDate;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;

	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Discount(int id,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String description,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") float amount,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") float percentage,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") Date initDate,
			@NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") Date endDate,
			boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.percentage = percentage;
		this.initDate = initDate;
		this.endDate = endDate;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}

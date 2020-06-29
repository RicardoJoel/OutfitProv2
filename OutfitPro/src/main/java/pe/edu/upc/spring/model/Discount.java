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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Discount")
public class Discount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(max=50, message="Máximo 50 caracteres")
	@NotEmpty(message="Campo obligatorio")
	@NotBlank(message="No puede estar en blanco")
	@Column(length=50, nullable=false)
	private String name;

	@Size(max=500, message="Máximo 500 caracteres")
	@Column(length=500, nullable=true)
	private String description;
	
	@Min(value=0, message="El valor debe estar entre 0 y 500.00")
	@Max(value=500, message="El valor debe estar entre 0 y 500.00")
	@Column(nullable=false)
	private float amount;
	
	@Min(value=0, message="El valor debe estar entre 0 y 100")
	@Max(value=100, message="El valor debe estar entre 0 y 100")
	@Column(nullable=false)
	private int percentage;
	
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
			@Size(max = 50, message = "Máximo 50 caracteres") @NotEmpty(message = "Campo obligatorio") @NotBlank(message = "No puede estar en blanco") String name,
			@Size(max = 500, message = "Máximo 500 caracteres") String description,
			@Min(value = 0, message = "El valor debe estar entre 0 y 500.00") @Max(value = 500, message = "El valor debe estar entre 0 y 500.00") float amount,
			@Min(value = 0, message = "El valor debe estar entre 0 y 100") @Max(value = 100, message = "El valor debe estar entre 0 y 100") int percentage,
			@NotNull(message = "Campo obligatorio") Date initDate, @NotNull(message = "Campo obligatorio") Date endDate,
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

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
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

package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Preference")
public class Preference implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne @JoinColumn(nullable=false)
	private Customer customer;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Mark mark;
	
	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private ClothingType clothingType;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public Preference() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Preference(int id, Customer customer, @NotNull(message = "Debes elegir una opción") Mark mark,
			@NotNull(message = "Debes elegir una opción") ClothingType clothingType, boolean enabled) {
		super();
		this.id = id;
		this.customer = customer;
		this.mark = mark;
		this.clothingType = clothingType;
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

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public ClothingType getClothingType() {
		return clothingType;
	}

	public void setClothingType(ClothingType clothingType) {
		this.clothingType = clothingType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

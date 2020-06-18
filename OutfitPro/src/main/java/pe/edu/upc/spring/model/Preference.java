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

@Entity
@Table(name="Preference")
public class Preference implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Mark mark;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private ClothingType clothingType;

	public Preference() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Preference(int id, boolean enabled, Mark mark, ClothingType clothingType) {
		super();
		this.id = id;
		this.enabled = enabled;
		this.mark = mark;
		this.clothingType = clothingType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public ClothingType getClothingType() {
		return clothingType;
	}

	public void setClothingType(ClothingType clothingType) {
		this.clothingType = clothingType;
	}

}

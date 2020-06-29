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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SaleDetail")
public class SaleDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Min(value=1, message="El valor debe estar entre 1 y 1'000,000")
	@Max(value=1000000, message="El valor debe estar entre 1 y 1'000,000")
	@Column(nullable=false)
	private int quantity;
	
	@Min(value=0, message="El valor debe estar entre 0 y 1'000,000")
	@Max(value=1000000, message="El valor debe estar entre 0 y 1'000,000")
	@Column(nullable=false)
	private float subtotal;
	
	@Min(value=0, message="El valor debe estar entre 0 y 1'000,000")
	@Max(value=1000000, message="El valor debe estar entre 0 y 1'000,000")
	@Column(nullable=false)
	private float discount;
	
	@Min(value=0, message="El valor debe estar entre 0 y 1'000,000")
	@Max(value=1000000, message="El valor debe estar entre 0 y 1'000,000")
	@Column(nullable=false)
	private float subtotalFinal;

	@NotNull(message="Debes elegir una opción")
	@ManyToOne @JoinColumn(nullable=false)
	private Clothing clothing;

	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;
	
	public SaleDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getSubtotalFinal() {
		return subtotalFinal;
	}

	public void setSubtotalFinal(float subtotalFinal) {
		this.subtotalFinal = subtotalFinal;
	}

	public Clothing getClothing() {
		return clothing;
	}

	public void setClothing(Clothing clothing) {
		this.clothing = clothing;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}

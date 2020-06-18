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
@Table(name="SaleDetail")
public class SaleDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private int quantity;
	
	@Column(nullable=false)
	private float subtotal;
	
	@Column(nullable=false)
	private float discount;
	
	@Column(nullable=false)
	private float subtotalFinal;
	
	@Column(nullable=false, columnDefinition = "boolean default true")
	private boolean enabled = true;

	@ManyToOne
	@JoinColumn(nullable=false)
	private Clothing clothing;

	public SaleDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleDetail(int id, int quantity, float subtotal, float discount, float subtotalFinal, boolean enabled,
			Clothing clothing) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.discount = discount;
		this.subtotalFinal = subtotalFinal;
		this.enabled = enabled;
		this.clothing = clothing;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Clothing getClothing() {
		return clothing;
	}

	public void setClothing(Clothing clothing) {
		this.clothing = clothing;
	}
	
	
}

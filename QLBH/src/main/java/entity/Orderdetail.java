package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderdetail database table.
 * 
 */
@Entity
@NamedQuery(name="Orderdetail.findAll", query="SELECT o FROM Orderdetail o")
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int price;

	private int quantity;

	//bi-directional many-to-one association to Hoadon
	@ManyToOne
	@JoinColumn(name="orderID")
	private Hoadon hoadon;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productID")
	private Product product;

	public Orderdetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Hoadon getHoadon() {
		return this.hoadon;
	}

	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
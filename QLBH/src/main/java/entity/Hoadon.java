package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hoadon database table.
 * 
 */
@Entity
@NamedQuery(name="Hoadon.findAll", query="SELECT h FROM Hoadon h")
public class Hoadon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="mo_ta")
	private String moTa;

	private int status;

	@Column(name="thanh_toan")
	private int thanhToan;

	private int total;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="nguoidungID")
	private User user;

	//bi-directional many-to-one association to Orderdetail
	@OneToMany(mappedBy="hoadon")
	private List<Orderdetail> orderdetails;

	public Hoadon() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoTa() {
		return this.moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getThanhToan() {
		return this.thanhToan;
	}

	public void setThanhToan(int thanhToan) {
		this.thanhToan = thanhToan;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Orderdetail addOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().add(orderdetail);
		orderdetail.setHoadon(this);

		return orderdetail;
	}

	public Orderdetail removeOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().remove(orderdetail);
		orderdetail.setHoadon(null);

		return orderdetail;
	}

}
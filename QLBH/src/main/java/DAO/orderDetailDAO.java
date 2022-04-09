package DAO;

import java.util.List;

import javax.persistence.EntityManager;

import Utils.JPAUtils;
import entity.Hoadon;
import entity.Orderdetail;

public class orderDetailDAO {
    EntityManager em;
    public orderDetailDAO() {
		// TODO Auto-generated constructor stub
	 this.em = JPAUtils.getEntityManager();
    }
    public Orderdetail insert(Orderdetail od)throws Exception {
    	try {
			this.em.getTransaction().begin();
			this.em.persist(od);
			this.em.clear();
			this.em.getTransaction().commit();
			return od;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
    }
    public List<Orderdetail> findHDCTbyHD(int hd){
    	List<Orderdetail> list = this.em.find(Hoadon.class, hd).getOrderdetails();
    	return list;
    }
}

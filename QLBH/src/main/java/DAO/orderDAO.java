package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Utils.JPAUtils;
import entity.Hoadon;

public class orderDAO {
	EntityManager em;

	public orderDAO() {
		// TODO Auto-generated constructor stub
		this.em = JPAUtils.getEntityManager();
	}

	public Hoadon insert(Hoadon o) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.persist(o);
			this.em.getTransaction().commit();
			return o;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}
	public Hoadon update(Hoadon o) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.merge(o);
			this.em.getTransaction().commit();
			return o;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}
	public List<Hoadon> findHoaDonByUser(int id){
		String jpql = "Select h from Hoadon h  where h.user.id = :id";
		TypedQuery<Hoadon> query = this.em.createQuery(jpql,Hoadon.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	public List<Hoadon> findHoaDonbystatus(int status){
		String jpql = "Select h from Hoadon h  where h.status= :id";
		TypedQuery<Hoadon> query = this.em.createQuery(jpql,Hoadon.class);
		query.setParameter("id", status);
		return query.getResultList();
	}
	public Hoadon find(int id) {
		return this.em.find(Hoadon.class, id);
	}
	
}

package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Utils.JPAUtils;
import entity.Category;

public class categoryDAO {

	EntityManager em;

	public categoryDAO() {
		// TODO Auto-generated constructor stub
		this.em = JPAUtils.getEntityManager();
	}

	public Category insert(Category c) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.persist(c);
			this.em.clear();
			this.em.getTransaction().commit();
			return c;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public Category update(Category c)throws Exception {
		  try {
			this.em.getTransaction().begin();
			this.em.merge(c);
			this.em.getTransaction().commit();
			return c;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	  }

	public void delete(Category c) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.remove(c);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public List<Category> findAll() {
		TypedQuery<Category> query = em.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	public Category find(int id) {
		return this.em.find(Category.class, id);
	}

	public List<Category> getCategorybyName(String name) {
		String jpql = "select c from Category c where c.ten like :cname";
		TypedQuery<Category> query = this.em.createQuery(jpql, Category.class);
		query.setParameter("cname", "%"+name+"%");
		return query.getResultList();
	}

}

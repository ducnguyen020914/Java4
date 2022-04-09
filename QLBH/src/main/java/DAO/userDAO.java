package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Utils.JPAUtils;
import entity.User;

public class userDAO {
	EntityManager em;

	public userDAO() {
		// TODO Auto-generated constructor stub
		this.em = JPAUtils.getEntityManager();
	}

	public User insert(User u) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.persist(u);
			this.em.clear();
			this.em.getTransaction().commit();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public User merge(User u) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.merge(u);
			this.em.getTransaction().commit();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public void delete(User u) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.remove(u);
			this.em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public User checklogin(String email) {
		String jpql = "select u from User u where u.email = :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", email);
		User u = query.getSingleResult();
		return u;
	}

	public List<User> findUserbyName(String name, int page, int size) {
		String jqpl = "select u from User u where u.hoTen like :name";
		TypedQuery<User> query = this.em.createQuery(jqpl, User.class);
		query.setParameter("name", "%" + name + "%");
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		return query.getResultList();
	}

	public long getcount(String name) {
		String jqpl = "select count(u) from User u where u.hoTen like :name";
		TypedQuery<Long> query = this.em.createQuery(jqpl, Long.class);
		query.setParameter("name", "%" + name + "%");
		return query.getSingleResult();
	}

	public User find(int id) {
		return this.em.find(User.class, id);
	}
}

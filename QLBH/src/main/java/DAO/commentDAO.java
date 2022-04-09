package DAO;

import java.util.List;

import javax.persistence.EntityManager;

import Utils.JPAUtils;
import entity.Comment;
import entity.Product;

public class commentDAO {
     EntityManager em;
     public commentDAO() {
		// TODO Auto-generated constructor stub
	  this.em = JPAUtils.getEntityManager();
     }
     public List<Comment> findcommentByproduct(int id){
    	 List<Comment> list = em.find(Product.class, id).getComments();
    	 return list;
     }
     public Comment create(Comment c)throws Exception {
    	try {
    		 this.em.getTransaction().begin();
        	 this.em.persist(c);
        	 this.em.clear();
        	 this.em.getTransaction().commit();
        	 return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}
     }
}

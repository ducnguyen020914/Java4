package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Utils.JPAUtils;
import entity.Category;
import entity.Product;

public class productDAO {

	EntityManager em;
	public productDAO() {
		// TODO Auto-generated constructor stub
		this.em = JPAUtils.getEntityManager();
	}
	 public Product insert(Product u) throws Exception{
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
	 public Product update(Product u) throws Exception{
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
	 public void delete(Product u) throws Exception{
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
	public List<Product> getnewProduct(){
		String jpql = "select p from Product p order by p.id desc";
		TypedQuery<Product> query = em.createQuery(jpql,Product.class);
		query.setFirstResult(0);
		query.setMaxResults(8);
		return query.getResultList();
	}
	public Product find(int id){
		return em.find(Product.class, id);
	}
	public List<Product> findSanPhamlienquan(Category theloai,int id){
		String jpql = "select p from Product p where p.category = :Theloai and p.id != :id";
		TypedQuery<Product> query = em.createQuery(jpql,Product.class);
		query.setParameter("Theloai", theloai);
		query.setParameter("id", id);
		return query.getResultList();
		
	}
	public List<Product> findBycategori(int id){
		List<Product> list = em.find(Category.class, id).getProducts();
		return list;
	}
	public List<Product> findProductByname(String productname ,int page,int size){
		String jqpl = "select p from Product p where p.ten Like :productname";
		TypedQuery<Product> query = this.em.createQuery(jqpl,Product.class);
		query.setParameter("productname", "%"+productname+"%");
		query.setFirstResult((page-1)*size);
		query.setMaxResults(size);
		return query.getResultList();
	}
     
     public long getcount(String name) {
    	 String jqpl = "select count(u) from Product u where u.ten like :name";
    	 TypedQuery<Long> query = this.em.createQuery(jqpl,Long.class);
    	 query.setParameter("name", "%"+name+"%");
    	 return query.getSingleResult();
     }
     
}

package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
     
	  public static EntityManager getEntityManager() {
		  EntityManagerFactory factory = Persistence.createEntityManagerFactory("QLBH");
		  return factory.createEntityManager();
	  }
}
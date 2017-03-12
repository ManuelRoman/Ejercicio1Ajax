package ejercicio1Ajax.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanDaoConexion{
	
	protected String unidadPersistencia;
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public BeanDaoConexion(String unidadPersistencia){
		this.unidadPersistencia = unidadPersistencia;
	}

	public void getConexion() throws Exception,EntityExistsException, IllegalStateException{
		if (this.em == null){
			this.emf = Persistence.createEntityManagerFactory(this.unidadPersistencia);
			this.em = emf.createEntityManager();
		}	
	}

	public void close() throws Exception,EntityExistsException, IllegalStateException {
		if (this.em != null){
			this.em.close();
			this.emf.close();
		}
		this.em = null;
		this.emf = null;
	}

}

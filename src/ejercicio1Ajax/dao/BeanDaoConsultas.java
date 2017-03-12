package ejercicio1Ajax.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ejercicio1Ajax.beans.*;

public class BeanDaoConsultas extends BeanDaoConexion {
	
	
	public BeanDaoConsultas(String unidadPersistencia){
		super(unidadPersistencia);
	}

	public String obtenerTituloFotograma(String dato)
			throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		String titulo = null;
		Query query = null;
		List<Fotograma> listaFotogramas = new ArrayList();
		Fotograma fotograma = null;
		try {
			String jpql = "SELECT f FROM Fotograma f WHERE f.titPelicula like ?1 order by f.titPelicula";
			query = em.createQuery(jpql);
			query.setParameter(1, dato+"%");
			listaFotogramas = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		if(!listaFotogramas.isEmpty()){
			fotograma = listaFotogramas.get(0);
			titulo = fotograma.getTitPelicula();
		}
		return titulo;
	}

	public List<Fotograma> obtenerFotogramas(String titulo) throws Exception, EntityExistsException, IllegalArgumentException {
		boolean conexionNula = false;
		if (em == null) {
			getConexion();
			conexionNula = true;
		}
		List<Fotograma> listaFotogramas = new ArrayList();
		Query query = null;
		try {
			String jpql = "SELECT f FROM Fotograma f WHERE f.titPelicula like ?1 order by f.titPelicula";
			query = em.createQuery(jpql);
			query.setParameter(1, titulo+"%");
			listaFotogramas = query.getResultList();
		} finally {
			if (conexionNula) {
				close();
			}
		}
		return listaFotogramas;
	}

}

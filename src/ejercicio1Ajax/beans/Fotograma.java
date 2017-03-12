package ejercicio1Ajax.beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fotogramas database table.
 * 
 */
@Entity
@Table(name="fotogramas")
@NamedQuery(name="Fotograma.findAll", query="SELECT f FROM Fotograma f")
public class Fotograma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idFotograma;

	private int anyoEstreno;

	private String archivo;

	private String director;

	private String genero;

	private String titPelicula;

	public Fotograma() {
	}

	public int getIdFotograma() {
		return this.idFotograma;
	}

	public void setIdFotograma(int idFotograma) {
		this.idFotograma = idFotograma;
	}

	public int getAnyoEstreno() {
		return this.anyoEstreno;
	}

	public void setAnyoEstreno(int anyoEstreno) {
		this.anyoEstreno = anyoEstreno;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTitPelicula() {
		return this.titPelicula;
	}

	public void setTitPelicula(String titPelicula) {
		this.titPelicula = titPelicula;
	}

}
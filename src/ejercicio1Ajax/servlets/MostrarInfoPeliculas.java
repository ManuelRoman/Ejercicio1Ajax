package ejercicio1Ajax.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejercicio1Ajax.beans.Fotograma;
import ejercicio1Ajax.dao.BeanDaoConsultas;


public class MostrarInfoPeliculas extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Fuente de datos.
	 */
	private String unidadPersistencia;
	
	/**
	 * Objeto encapsula toda la información a nivel de aplicación.
	 */
	private ServletContext sc;
	
	public MostrarInfoPeliculas(){
		super();
	}
	
	/**
	 * Inicializa el servlet, y le proporciona un objeto, ServletConfig con
	 * información de nivel de aplicación sobre el contexto de datos que rodea
	 * al servlet en el contenedor web.
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// Imprescindible llamar a super.init(config) para tener acceso a la
		// configuración del servlet a nivel de contenedor web.
		super.init(config);
		// En este punto se procedería a obtener las referencias a las fuentes
		// de datos de la aplicación.
		sc = config.getServletContext();
		setUPApp((String) sc.getInitParameter("unidadPersistencia"));
		sc.setAttribute("upbdfotogramas", getUPApp());
		sc.setAttribute("appOperativa", "true");
		if (getUPApp() == null){
			System.out.println("la unidad de persistencia es null.");
			sc.setAttribute("appOperativa", "false");
		}
	}
	
	/**
	 * Prepara el servlet para su eliminación.
	 */
	public void destroy() {
		// Elimina el datasource del ámbito de aplicación, liberando todos los
		// recursos que tuviera asignados.
		sc.removeAttribute("upbdfotogramas");
		sc.removeAttribute("appOperativa");
		// Elimina el ámbito de aplicación.
		sc = null;
	}
	
	/**
	 * Establece la fuente de datos para la aplicación.
	 */
	public void setUPApp(String unidadPersistencia) {
		this.unidadPersistencia = unidadPersistencia;
	}

	/**
	 * Obtiene la referencia a la fuente de datos de la aplicación.
	 */
	public String getUPApp() {
		return this.unidadPersistencia;
	}
	
	/**
	 * Procesamiento de la petición en modo GET. Se reenvía al método doPost()
	 * @param request Petición
	 * @param response Respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * Procesamiento de la petición en modo POST
	 * @param request Petición
	 * @param response Respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datos = request.getParameter("titulo");
		String buscar=request.getParameter("titulo2");
		System.out.println("Dato a buscar: "+ datos);
		System.out.println("Buscar pelicula si/no: "+ buscar);
		BeanDaoConsultas daoConsultas = (BeanDaoConsultas) sc.getAttribute("daoConsultas");
		if (daoConsultas == null){
			daoConsultas = new BeanDaoConsultas(getUPApp());
		}
		if (datos!=null & buscar==null){
			response.setContentType("text/plain;charset=UTF-8");
	        PrintWriter out = response.getWriter();
			try{
				daoConsultas.getConexion();
				String tituloCompleto = daoConsultas.obtenerTituloFotograma(datos);
				System.out.println("Titulo completo ofrecido: "+tituloCompleto);
				if(tituloCompleto!=null){
					out.println(tituloCompleto);
				}
				out.close();
			} catch (EntityExistsException e) {
				System.out.println("Excepción de existencia previa de la entidad.");
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("Excepción de instancia no es tipo entidad.");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Error desconocido en transacción.");
				e.printStackTrace();
			} finally {
				try {
					daoConsultas.close();
				} catch (Exception e) {
					System.out.println("Error al cerrar la conexión.");
					e.printStackTrace();
				}
			}
		}
		if (buscar!=null){
			List<Fotograma> peliculas = new ArrayList();
			StringBuilder objJSON = null;
			System.out.print("Pelicula a mostrar todos los datos: "+request.getParameter("titulo2"));
			try{
				daoConsultas.getConexion();
				peliculas = daoConsultas.obtenerFotogramas(buscar);
			} catch (EntityExistsException e) {
				System.out.println("Excepción de existencia previa de la entidad.");
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("Excepción de instancia no es tipo entidad.");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Error desconocido en transacción.");
				e.printStackTrace();
			} finally {
				try {
					daoConsultas.close();
				} catch (Exception e) {
					System.out.println("Error al cerrar la conexión.");
					e.printStackTrace();
				}
			}
			PrintWriter out = response.getWriter();
			if (peliculas.size() >0) {
				objJSON = new StringBuilder("[");
				for (int i = 0; i<peliculas.size(); i++) {
					objJSON.append("{titulo:'");
					objJSON.append(peliculas.get(i).getTitPelicula());
					objJSON.append("',director:'");
					objJSON.append(peliculas.get(i).getDirector());
					objJSON.append("',genero:'");
					objJSON.append(peliculas.get(i).getGenero());
					objJSON.append("',fecha:'");
					objJSON.append(peliculas.get(i).getAnyoEstreno());
					objJSON.append("'},");
				}
				objJSON.replace(objJSON.length()-1, objJSON.length(), "]");
			} else {
				objJSON = new StringBuilder("[]");
			}
			System.out.println("Cadena que se enviará: "+objJSON.toString());
			out.println(objJSON.toString());
			out.close();
		}
		
	}

}

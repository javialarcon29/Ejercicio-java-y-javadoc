package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.BaseDatos;

/**
 * Este servlet sera el que procese los datos enviados por el formulario y los
 * valide contra la base de datos para comprobar si las credenciales son
 * correctas o no.
 * 
 * Dependiendo del resultado de la validacion mostrara la pagina del formulario
 * de nuevo o la pantalla de bienvenida al usuario que hizo la autenticacion
 * 
 * @author Javier Alarcon
 * @version 1.0
 * @see HttpServlet#HttpServlet()
 */
public class ServletProcesado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Atributos

	/**
	 * Objeto donde guardamos los datos de conexion
	 */
	private final BaseDatos datos;

	/**
	 * Variable donde guardaremos el nombre del usuario
	 */
	private String nombre;

	/**
	 * Variable donde guardaremos los apellidos del usuario
	 */
	private String apellidos;

	/**
	 * Constructor para inicializar el objeto BaseDatos
	 */
	public ServletProcesado() {
		datos = new BaseDatos();
	}

	// Metodos protegidos

	/**
	 * Procesa la peticion Http del tipo Post
	 * 
	 * @return
	 *         <ul>
	 *         <li>Muestra la pagina de bienvenida al usuario si la autencicacion
	 *         fue correcta</li>
	 *         <li>Devuelve al formulario si la autenticacion fallo</li>
	 *         </ul>
	 * 
	 * @param Peticion  que trae los datos del formulario
	 * @param Respuesta con la que devolveremos una pagina u otra
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (validarUsuario(username, password)) {
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellidos", apellidos);
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Error en la autenticación");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	// Metodos privados

	/**
	 * Metodo que busca el usuario en la base de datos y devuelve verdadero o falso
	 * dependiendo de si las credenciales son validas, ademas si el usuario y
	 * contrasena son validos guarda la informacion del usuario en las variables de
	 * nombre y apellido
	 * 
	 * @param Usuario    que viene en la peticion
	 * @param Contrasena que viene en la peticion
	 * @return
	 *         <ul>
	 *         <li>true: autenticacion correcta</li>
	 *         <li>false: fallo en la autenticacion</li>
	 *         </ul>
	 */
	private boolean validarUsuario(String user, String password) {

		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM usuarios WHERE username = '" + user + "'";

		try {
			conexion = obtenerConexion();
			st = conexion.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				if (rs.getString("password").compareTo(password) == 0) {
					nombre = rs.getString("nombre");
					apellidos = rs.getString("apellidos");
					return true;
				}

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				st.close();
				rs.close();
				conexion.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}

		return false;
	}

	/**
	 * Metodo para obtener la conexion a la base de datos
	 * 
	 * @return
	 *         <ul>
	 *         <li>Instancia de conexion a la base de datos</li>
	 *         </ul>
	 */

	private Connection obtenerConexion() {
		Connection conexion = null;
		try {
			Class.forName(datos.getDriver());
			conexion = DriverManager.getConnection(datos.getUrl(), datos.getUser(), datos.getPass());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return conexion;

	}

}

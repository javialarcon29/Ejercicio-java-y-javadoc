package clases;

import javax.servlet.http.HttpServlet;

/**
 * Esta clase contiene los datos de conexion a la base de datos para cargarlos a
 * traves de sus metodos getters en el metodo del servlet
 * 
 * @author Javier Alarcon
 * @version 1.0
 * 
 */
public class BaseDatos {

	// Atributos

	/**
	 * Variables necesarias para la conexion
	 */
	private String url, user, pass, driver;

	// Constructor

	/**
	 * Constructor donde inicializamos las variables
	 */
	public BaseDatos() {
		this.url = "jdbc:mysql://localhost:3306/javitarea";
		this.user = "root";
		this.pass = "";
		this.driver = "com.mysql.cj.jdbc.Driver";
	}

	// Metodos publicos

	/**
	 * Metodos getters para obtener los atributos
	 */
	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getDriver() {
		return driver;
	}

}

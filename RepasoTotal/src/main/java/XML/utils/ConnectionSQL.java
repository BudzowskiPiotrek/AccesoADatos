package XML.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {

	private final String RUTA = "jdbc:mysql://localhost/";
	private final String USUARIO = "root";
	private final String PASS = "";

	public Connection conectar(String nombre) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(RUTA + nombre, USUARIO, PASS);
		} catch (SQLException e) {
			System.err.println("Error al conectar");
			System.err.println("Detalle de error: " + e.getMessage());
		}
		return con;
	}
}

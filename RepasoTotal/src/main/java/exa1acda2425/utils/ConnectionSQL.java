package exa1acda2425.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
	private final String RUTA = "jdbc:mysql://localhost/vuelos";
	private final String USUARIO = "root";
	private final String PASS = "";

	public Connection conectar() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(RUTA, USUARIO, PASS);
		} catch (SQLException e) {
			System.err.println("Error al conectar");
			System.err.println("Detalle de error: " + e.getMessage());
		}
		return con;
	}
}

package exa1acda2526.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
	private final String RUTA = "jdbc:mysql://localhost/astronomia";
	private final String USUARIO = "root";
	private final String PASS = "";

	public Connection conectar() {
		Connection con = null;

		try {
			con = DriverManager.getConnection(RUTA,USUARIO , PASS);
		} catch (SQLException e) {
			System.err.println("Error al intentar establecer la conexion");
			System.err.println("Detalle del error " + e.getMessage());
		}
		return con;
	}
}

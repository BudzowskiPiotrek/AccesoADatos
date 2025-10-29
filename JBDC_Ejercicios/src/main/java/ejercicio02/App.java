package ejercicio02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	// Crea una base de datos en MySQL WorkBench con tres campos, introducir cinco
	// registros y crear una aplicación java mediante JDBC que recorra todos sus
	// registros visualizando su contenido.
	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "");

			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			
			ResultSet resul = sentencia.executeQuery(sql);

			while (resul.next()) {
				System.out.printf("%d, %s, %s, %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}

			resul.close(); 
			sentencia.close(); 
			conexion.close(); 

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

package practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

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

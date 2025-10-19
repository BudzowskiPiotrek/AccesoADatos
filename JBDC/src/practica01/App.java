package practica01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			// cargar el driver
			Class.forName("com.mysql.jdbc.Driver");

			// establecemos la conexion co
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "");

			// preparamos la consulta
			Statement sentencia = conexion.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resul = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila
			// Se hace un buclee mientras haya regisros y se van mostrando
			while (resul.next()) {
				System.out.printf("%d, %s, %s, %n", resul.getInt(1), resul.getString(2), resul.getString(3));
			}

			resul.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

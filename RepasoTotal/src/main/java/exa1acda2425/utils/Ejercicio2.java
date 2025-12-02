package exa1acda2425.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio2 {
	private ConnectionSQL conSQL = new ConnectionSQL();

	public boolean obtenerDatosRecaudacion() {
		String sql = "SELECT V.TIPO_VUELO, P.CLASE, SUM(P.PVP) AS RECAUDACION_TOTAL FROM vuelo V INNER JOIN pasaje P"
				+ " ON V.IDENTIFICADOR = P.IDENTIFICADOR GROUP BY V.TIPO_VUELO, P.CLASE ORDER BY V.TIPO_VUELO, P.CLASE;";
		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {

				boolean valor = false;
				while (rs.next()) {
					valor = true;

					System.out.printf("Tipo Vuelo: %s | Clase: %s | Recaudacion Total: %.2f \n", rs.getString(1),
							rs.getString(2), rs.getFloat(3));
				}
				if (!valor) {
					System.out.println("No se encontro ningunas recaudaciones en base de datos");
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR al ejecutar la consulta para obtener recaudaciones");
			System.err.println("Detalle: " + e.getMessage());
			return false;
		}
		return true;
	}
}

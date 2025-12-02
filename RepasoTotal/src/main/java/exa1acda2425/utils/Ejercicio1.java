package exa1acda2425.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio1 {
	private ConnectionSQL conSQL = new ConnectionSQL();

	public boolean obtenerDatosPersonal(int mes) {
		String sql = "SELECT V.IDENTIFICADOR, V.AEROPUERTO_ORIGEN, V.AEROPUERTO_DESTINO, V.FECHA_VUELO,"
				+ " Pe.NOMBRE AS NOMBRE_PERSONAL, Pe.CATEGORIA AS CATEGORIA_PERSONAL, T.PUESTO,"
				+ " Pa.NOMBRE AS NOMBRE_PASAJERO, P.NUMASIENTO, P.CLASE FROM vuelo V LEFT JOIN pasaje P"
				+ " ON V.IDENTIFICADOR = P.IDENTIFICADOR LEFT JOIN pasajero Pa ON P.PASAJERO_COD = Pa.COD LEFT JOIN tripulacion T"
				+ " ON V.IDENTIFICADOR = T.VUELO_IDENTIFICADOR LEFT JOIN personal Pe ON T.PERSONAL_CODIGO = Pe.CODIGO WHERE MONTH(V.FECHA_VUELO) = ? ;";

		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, mes);

			try (ResultSet rs = ps.executeQuery()) {

				boolean valor = false;
				while (rs.next()) {
					valor = true;

					System.out.printf(
							"Identificador: %s | Origen: %s | Destino: %s | Fecha Vuelo: %s | Personal: %s (%s - %s) | Pasajero: %s | Asiento: %d | Clase: %s%n",
							rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10));
				}
				if (!valor) {
					System.out.println("No se encontraron vuelos para el mes " + mes + ".");
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR al ejecutar la consulta para el mes " + mes);
			System.err.println("Detalle: " + e.getMessage());
			return false;
		}
		return true;
	}
}

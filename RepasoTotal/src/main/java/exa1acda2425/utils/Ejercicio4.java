package exa1acda2425.utils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import exa1acda2425.*;

import org.neodatis.odb.ODB;

public class Ejercicio4 {
	private ConnectionSQL conSQL = new ConnectionSQL();
	private ConnectionNeodatis conNeo = new ConnectionNeodatis();

	public boolean insertar() {
		String sqlVuelos = "SELECT IDENTIFICADOR, AEROPUERTO_ORIGEN, AEROPUERTO_DESTINO, TIPO_VUELO, FECHA_VUELO FROM vuelo;";
		ODB odb = null;

		try (Connection con = conSQL.conectar();
				PreparedStatement ps = con.prepareStatement(sqlVuelos);
				ResultSet rs = ps.executeQuery()) {
			odb = conNeo.openDB();

			while (rs.next()) {
				Vuelo vuelo = new Vuelo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5));

				String identificador = vuelo.getIdentificador();


				vuelo.setTripulacion(obtenerTripulacion(con, identificador));
				vuelo.setPasajes(obtenerPasaje(con, identificador));

				odb.store(vuelo);
			}
			odb.close();
			return true;

		} catch (Exception e) {
			System.err.println("Error general en el proceso de volcado: " + e.getMessage());
			if (odb != null)
				try {
					odb.close();
				} catch (Exception ex) {
					System.err.println("Error al cerrar ODB: " + ex.getMessage());
				}
			return false;
		}
	}

	public List<Pasaje> obtenerPasaje(Connection con, String idVuelo) {
		List<Pasaje> listaPasaje = new ArrayList<>();
		String sql = "SELECT " + "Pa.COD, Pa.NOMBRE, Pa.TLF, Pa.DIRECCION, Pa.PAIS, "
				+ "P.PASAJERO_COD, P.IDENTIFICADOR, P.NUMASIENTO, P.CLASE, P.PVP "
				+ "FROM pasaje P INNER JOIN pasajero Pa ON P.PASAJERO_COD = Pa.COD WHERE P.IDENTIFICADOR = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, idVuelo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					Pasajero pa = new Pasajero(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5));
					
					Pasaje pj = new Pasaje(rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9),
							rs.getFloat(10), pa);
					listaPasaje.add(pj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPasaje;
	}

	public List<Tripulacion> obtenerTripulacion(Connection con, String idVuelo) {
		List<Tripulacion> listaTripulacion = new ArrayList<>();
		String sql = "SELECT " + "T.VUELO_IDENTIFICADOR, T.PERSONAL_CODIGO, T.PUESTO, "
				+ "Pe.CODIGO, Pe.NOMBRE, Pe.CATEGORIA "
				+ "FROM tripulacion T INNER JOIN personal Pe ON T.PERSONAL_CODIGO = Pe.CODIGO "
				+ "WHERE T.VUELO_IDENTIFICADOR = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, idVuelo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					Personal p = new Personal(rs.getInt(4), rs.getString(5), rs.getString(6));
					Tripulacion t = new Tripulacion(rs.getString(1), rs.getInt(2), rs.getString(3), p);
					listaTripulacion.add(t);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaTripulacion;
	}
}
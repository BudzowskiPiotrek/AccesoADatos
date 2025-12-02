package exa1acda2425.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio3 {
	private ConnectionSQL conSQL = new ConnectionSQL();

	public Document crearDocumentoXML(int mes, Document d) {
		String sql = "SELECT IDENTIFICADOR, AEROPUERTO_ORIGEN, AEROPUERTO_DESTINO, FECHA_VUELO FROM vuelo WHERE MONTH(FECHA_VUELO) = ?";
		Element vuelos = d.createElement("vuelos");

		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, mes);

			try (ResultSet rs = ps.executeQuery()) {

				boolean valor = false;
				while (rs.next()) {
					String identificador = rs.getString(1);
					valor = true;

					Element vuelo = d.createElement("vuelo");
					vuelo.setAttribute("identificador", identificador);

					Element origen = d.createElement("origen");
					origen.appendChild(d.createTextNode(rs.getString(2)));
					vuelo.appendChild(origen);

					Element destino = d.createElement("destino");
					destino.appendChild(d.createTextNode(rs.getString(3)));
					vuelo.appendChild(destino);

					Element fecha = d.createElement("fecha");
					fecha.appendChild(d.createTextNode(rs.getString(4)));
					vuelo.appendChild(fecha);

					meterDatosTripulacion(vuelo, identificador, con, d);
					meterDatosPasajeros(vuelo, identificador, con, d);

					vuelos.appendChild(vuelo);
				}
				if (!valor) {
					System.out.println("No se encontraron vuelos para el mes " + mes);
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR al ejecutar la consulta para el mes " + mes);
			System.err.println("Detalle: " + e.getMessage());
			return null;
		}
		d.getDocumentElement().appendChild(vuelos);
		return d;
	}

	private void meterDatosTripulacion(Element vuelo, String identificador, Connection con, Document d) {
		String sql = "SELECT Pe.NOMBRE AS NOMBRE_PERSONAL, Pe.CATEGORIA AS CATEGORIA_PERSONAL, T.PUESTO FROM tripulacion T INNER JOIN personal Pe"
				+ " ON T.PERSONAL_CODIGO = Pe.CODIGO WHERE T.VUELO_IDENTIFICADOR = ?;";
		Element tripulacion = d.createElement("tripulacion");

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, identificador);

			try (ResultSet rs = ps.executeQuery()) {
				boolean valor = false;
				while (rs.next()) {
					valor = true;

					Element personal = d.createElement("personal");
					personal.setAttribute("nombre", rs.getString(1));

					Element categoria = d.createElement("categoria");
					categoria.appendChild(d.createTextNode(rs.getString(2)));
					personal.appendChild(categoria);

					Element puesto = d.createElement("puesto");
					puesto.appendChild(d.createTextNode(rs.getString(3)));
					personal.appendChild(puesto);

					tripulacion.appendChild(personal);
				}

				if (valor) {
					vuelo.appendChild(tripulacion);
				} else {
					System.out.println("No se encontraron tripulantes para el identificador " + identificador + ".");
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR al ejecutar la consulta de tripulación para el vuelo " + identificador);
			System.err.println("Detalle: " + e.getMessage());
		}
	}

	private void meterDatosPasajeros(Element vuelo, String identificador, Connection con, Document d) {
		String sql = "SELECT Pa.NOMBRE AS NOMBRE_PASAJERO, P.NUMASIENTO, P.CLASE FROM pasaje P INNER JOIN pasajero Pa"
				+ " ON P.PASAJERO_COD = Pa.COD WHERE P.IDENTIFICADOR = ?;";

		Element pasaje = d.createElement("pasaje");

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, identificador);

			try (ResultSet rs = ps.executeQuery()) {
				boolean hayPasajeros = false;
				while (rs.next()) {
					hayPasajeros = true;

					Element pasajero = d.createElement("pasajero");
					pasajero.setAttribute("nombre", rs.getString(1));

					Element asiento = d.createElement("asiento");
					asiento.appendChild(d.createTextNode(rs.getString(2)));
					pasajero.appendChild(asiento);

					Element clase = d.createElement("clase");
					clase.appendChild(d.createTextNode(rs.getString(3)));
					pasajero.appendChild(clase);

					pasaje.appendChild(pasajero);
				}
				if (hayPasajeros) {
					vuelo.appendChild(pasaje);
				} else {
					System.out.println("No se encontraron pasajeros para el identificador " + identificador + ".");
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR al ejecutar la consulta de pasaje para el vuelo " + identificador);
			System.err.println("Detalle: " + e.getMessage());
		}
	}
}

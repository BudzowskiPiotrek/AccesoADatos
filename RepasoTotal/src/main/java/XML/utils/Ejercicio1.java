package XML.utils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio1 {
	ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_BD = "practicanaves";

	public Document convertirXML(Document doc, String str) {
		String sql = "SELECT n.codigo_iata, n.nombre_empresa, n.pais_origen, b.mmsi, b.nombre, b.capacidad_teu, b.buque_nodriza"
				+ " FROM navieras n Left JOIN buques b ON n.codigo_iata=b.codigo_naviera WHERE n.nombre_empresa = ?;";
		Element naviera = doc.createElement("naviera");
		Element flota = doc.createElement("flota_activa");
		try (Connection con = conSQL.conectar(NOMBRE_BD); PreparedStatement pr = con.prepareStatement(sql)) {
			pr.setString(1, str);
			int control = 0;
			try (ResultSet rs = pr.executeQuery()) {
				boolean valor = false;
				while (rs.next()) {
					valor = true;
					if (control == 0) {
						naviera.setAttribute("codigo", rs.getString(1));
						naviera.setAttribute("empresa", rs.getString(2));
						Element pais = doc.createElement("pais_sede");
						pais.appendChild(doc.createTextNode(rs.getString(3)));
						naviera.appendChild(pais);
						control++;
					}

					Element buque = doc.createElement("buque");
					buque.setAttribute("id", rs.getString(4));
					buque.setAttribute("nombre", rs.getString(5));

					Element especificaciones = doc.createElement("especificaciones");
					especificaciones.setAttribute("capacidad", rs.getString(6));
					buque.appendChild(especificaciones);

					Element tipo = doc.createElement("tipo_operacion");
					if (rs.getString(7) == null) {
						tipo.appendChild(doc.createTextNode("Principal"));
						buque.appendChild(tipo);
					} else {
						tipo.appendChild(doc.createTextNode("Auxiliar (Nodriza: " + rs.getString(7) + ")"));
						buque.appendChild(tipo);
					}
					flota.appendChild(buque);
				}
				naviera.appendChild(flota);
				if (!valor) {
					System.out.println("No se encontro ningun registro");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta, Detalle del error:" + e.getMessage());
		}
		doc.getDocumentElement().appendChild(naviera);
		return doc;
	}

}

package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio9 {
	private ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_BD = "practica_dnd";

	public Document convertirXML(Document doc) {
		String sql = "SELECT * FROM campanas c LEFT JOIN personajes p ON c.id_campana=p.id_campana LEFT JOIN objetos o ON p.id_personaje=o.id_personaje ORDER BY c.nombre_campana, p.nombre_pj;";
		Element campana = null;
		Element personajes = null;
		Element inventario = null;
		Element objeto = null;

		try (Connection con = conSQL.conectar(NOMBRE_BD); PreparedStatement ps = con.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
				String campanaAux = "";
				String pjAux = "";

				while (rs.next()) {
					String campanaActual = rs.getString("nombre_campana");
					if (campanaActual != null && !campanaAux.equals(campanaActual)) {
						campana = doc.createElement("campaña");
						campana.setAttribute("nombre", rs.getString("nombre_campana"));
						campana.setAttribute("dm", rs.getString("dm_name"));
						doc.getDocumentElement().appendChild(campana);
						personajes = doc.createElement("personajes");
						campana.appendChild(personajes);

						campanaAux = campanaActual;
						pjAux = "";
					}
					String pjActual = rs.getString("nombre_pj");
					if (pjActual != null && !pjAux.equals(pjActual)) {
						Element personaje = doc.createElement("personaje");
						personaje.setAttribute("nombre", rs.getString("nombre_pj"));
						personajes.appendChild(personaje);
						inventario = doc.createElement("inventario");
						personaje.appendChild(inventario);
						pjAux = pjActual;
					}
					String nombreObj = rs.getString("nombre_objeto");
					if (nombreObj != null) {
						objeto = doc.createElement("objeto");
						objeto.setAttribute("rareza", rs.getString("rareza"));
						objeto.appendChild(doc.createTextNode(rs.getString("nombre_objeto")));
						inventario.appendChild(objeto);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al intentar realizar la consulta, Detalle del error: " + e.getMessage());
		}
		return doc;
	}
}

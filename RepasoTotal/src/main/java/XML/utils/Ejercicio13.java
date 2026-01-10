package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio13 {
	private ConnectionSQL conSQL = new ConnectionSQL();

	public Document convertirXML(Document doc, String condicion) {
		String s = "SELECT * FROM islas i LEFT JOIN tripulaciones t ON i.id_isla = t.id_isla_actual LEFT JOIN personajes p ON t.id_tripulacion = p.id_tripulacion LEFT JOIN frutas_diablo f ON p.id_personaje = f.id_personaje "
				+ " WHERE i.mar = ? ORDER BY i.id_isla, t.id_tripulacion, p.id_personaje";
		Element islaNodo = null;
		Element tripuNodo = null;
		Element personajes = null;
		Element pjNodo = null;

		int auxIsla = -1;
		int auxTripu = -1;
		int auxPj = -1;

		try (Connection con = conSQL.conectar("practica_one_piece_db");
				PreparedStatement ps = con.prepareStatement(s)) {
			ps.setString(1, condicion);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int idIsla = rs.getInt("id_isla");
					if (idIsla != 0 && auxIsla != idIsla) {
						islaNodo = doc.createElement("Isla");
						islaNodo.setAttribute("nombre", rs.getString("nombre_isla"));
						doc.getDocumentElement().appendChild(islaNodo);

						auxIsla = idIsla;
						auxTripu = -1;
					}

					int idTripu = rs.getInt("id_tripulacion");
					if (idTripu != 0 && auxTripu != idTripu) {
						tripuNodo = doc.createElement("Tripulacion");
						tripuNodo.setAttribute("nombre", rs.getString("nombre_tripulacion"));
						tripuNodo.setAttribute("barco", rs.getString("barco"));
						personajes = doc.createElement("personajes");
						tripuNodo.appendChild(personajes);
						
						islaNodo.appendChild(tripuNodo);

						auxTripu = idTripu;
						auxPj = -1;
					}

					int idPj = rs.getInt("id_personaje");
					if (idPj != 00 && auxPj != idPj) {
						pjNodo = doc.createElement("Personaje");
						pjNodo.setAttribute("nombre", rs.getString("nombre_pj"));
						pjNodo.setAttribute("recompensa", rs.getString("recompensa_actual"));

						personajes.appendChild(pjNodo);
						auxPj = idPj;
					}

					String fruta = rs.getString("nombre_fruta");
					if (fruta != null) {
						Element frutaNodo = doc.createElement("fruta");
						frutaNodo.setAttribute("tipo", rs.getString("tipo_fruta"));
						frutaNodo.appendChild(doc.createTextNode(rs.getString("nombre_fruta")));
						pjNodo.appendChild(frutaNodo);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar consulta Detalles del error:" + e.getMessage());
		}
		return doc;
	}
}

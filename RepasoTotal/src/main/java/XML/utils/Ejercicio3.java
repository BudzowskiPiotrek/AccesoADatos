package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio3 {
	ConnectionSQL conSQL = new ConnectionSQL();

	public Document crearDocumentoXML(Document doc, String condicion) {
		String sql = "SELECT g.nombre as nom_g, g.tipo, e.nombre as nom_e, p.es_habitable, p.nombre as nom_p "
				+ "FROM galaxias g left JOIN estrellas e ON g.id_galaxia=e.id_galaxia Left JOIN planetas p ON e.id_estrella=p.id_estrella WHERE g.id_galaxia = ? Order by e.nombre;";
		
		Element listado = doc.createElement("listado_estrellas");
		int control = 0;
		String estrellaActual = "";
		Element estrella = null;
		Element planetas = null;

		try (Connection con = conSQL.conectar("practica_astronomia");
				PreparedStatement pr = con.prepareStatement(sql)) {

			pr.setString(1, condicion);

			try (ResultSet rs = pr.executeQuery()) {
				while (rs.next()) {
					if (control == 0) {
						doc.getDocumentElement().setAttribute("galaxia", rs.getString("nom_g"));
						doc.getDocumentElement().setAttribute("tipo", rs.getString("tipo"));
						control++;
					}

					String nombreE = rs.getString("nom_e");

					if (nombreE != null && !nombreE.equals(estrellaActual)) {
						estrellaActual = nombreE;

						estrella = doc.createElement("estrella");
						estrella.setAttribute("nombre", nombreE);
						planetas = doc.createElement("planetas");

						estrella.appendChild(planetas);
						listado.appendChild(estrella);
					}

					String nombreP = rs.getString("nom_p");

					if (nombreP != null) {
						Element planeta = doc.createElement("planeta");
						planeta.setAttribute("habitable", rs.getInt("es_habitable") == 1 ? "si" : "no");
						planeta.appendChild(doc.createTextNode(nombreP));
						planetas.appendChild(planeta);
					}
				}
				doc.getDocumentElement().appendChild(listado);
			}
		} catch (SQLException e) {
			System.err.println("Error SQL: " + e.getMessage());
		}
		return doc;
	}
}

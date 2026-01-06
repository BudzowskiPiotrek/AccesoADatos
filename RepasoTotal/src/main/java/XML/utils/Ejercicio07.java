package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio07 {
	private ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_BD = "practica_clinicaveterinaria";

	public Document convertirXML(Document doc, String condicion) {
		String sql = "SELECT * FROM dueños d LEFT JOIN pacientes p ON d.id_dueño=p.id_dueño LEFT JOIN visitas v ON p.id_paciente=v.id_paciente WHERE d.nombre = ? ORDER BY nombre_mascota;";

		try (Connection con = conSQL.conectar(NOMBRE_BD); PreparedStatement pr = con.prepareStatement(sql)) {
			pr.setString(1, condicion);

			try (ResultSet rs = pr.executeQuery()) {
				int control = 0;
				Element dueno = null;
				Element mascotas = null;
				Element visitas = null;
				String nombreAux = "";
				while (rs.next()) {

					if (control == 0) {
						dueno = doc.createElement("dueño");
						mascotas = doc.createElement("mascotas");
						dueno.setAttribute("nombre", rs.getString("nombre"));
						dueno.appendChild(mascotas);
						doc.getDocumentElement().appendChild(dueno);
						control++;
					}

					String nombre = rs.getString("nombre_mascota");

					if (nombre != null && !nombre.equals(nombreAux)) {
						Element mascota = doc.createElement("mascota");
						mascota.setAttribute("nombre", nombre);
						mascota.setAttribute("especie", rs.getString("especie"));
						visitas = doc.createElement("visitas");
						mascota.appendChild(visitas);
						mascotas.appendChild(mascota);

						nombreAux = nombre;
					}

					Element visita = doc.createElement("visita");
					visita.setAttribute("fecha", String.valueOf(rs.getDate("fecha")));
					visita.setAttribute("precio", String.valueOf(rs.getFloat("precio")));
					visita.appendChild(doc.createTextNode(rs.getString("diagnostico")));
					visitas.appendChild(visita);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al intentar realizar la consulta, Detalle del error: " + e.getMessage());
		}
		return doc;
	}
}

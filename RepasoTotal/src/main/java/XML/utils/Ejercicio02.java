package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio02 {
	ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_BD = "practica_esports";

	public Document convertirXML(Document doc, String requisito) {
		String sql = "SELECT t.id_torneo, t.nombre AS nom_t, t.videojuego, t.premio_total, t.ciudad_final, "
				+ "p.marca, p.tipo_patrocinio, p.aportacion_economica, " + "e.nombre_tag, e.pais, e.ranking_mundial "
				+ "FROM torneos t " + "LEFT JOIN patrocinadores p ON t.id_torneo = p.id_torneo "
				+ "LEFT JOIN equipos e ON t.id_torneo = e.id_torneo WHERE t.id_torneo = ?;";
		Element info = doc.createElement("info_general");
		Element lista = doc.createElement("lista_participantes");
		Element partners = doc.createElement("partners_oficiales");

		try (Connection con = conSQL.conectar(NOMBRE_BD); PreparedStatement pr = con.prepareStatement(sql)) {
			pr.setString(1, requisito);
			try (ResultSet rs = pr.executeQuery()) {
				boolean valor = false;
				int control = 0;
				List<String> equipos = new ArrayList<>();
				List<String> patrocinadores = new ArrayList<>();

				while (rs.next()) {
					if (control == 0) {
						valor = true;
						control++;
						doc.getDocumentElement().setAttribute("id", rs.getString("id_torneo"));
						doc.getDocumentElement().setAttribute("juego", rs.getString("videojuego"));

						Element nombre = doc.createElement("nombre");
						nombre.appendChild(doc.createTextNode(rs.getString("nom_t")));
						info.appendChild(nombre);

						Element premio = doc.createElement("premio_acumulado");
						premio.setAttribute("moneda", "EURO");
						premio.appendChild(doc.createTextNode(String.valueOf(rs.getInt("premio_total"))));
						info.appendChild(premio);

						Element sede = doc.createElement("sede");
						sede.appendChild(doc.createTextNode(rs.getString("ciudad_final")));
						info.appendChild(sede);

						doc.getDocumentElement().appendChild(info);
					}

					if (rs.getString("nombre_tag") != null && !equipos.contains(rs.getString("nombre_tag"))) {
						equipos.add(rs.getString("nombre_tag"));

						Element equipo = doc.createElement("equipo");
						equipo.setAttribute("ranking", String.valueOf(rs.getInt("ranking_mundial")));

						Element nombre = doc.createElement("nombre");
						nombre.appendChild(doc.createTextNode(rs.getString("nombre_tag")));
						equipo.appendChild(nombre);

						Element region = doc.createElement("region");
						region.appendChild(doc.createTextNode(rs.getString("pais")));
						equipo.appendChild(region);

						lista.appendChild(equipo);
					}

					if (rs.getString("marca") != null && !patrocinadores.contains(rs.getString("marca"))) {
						patrocinadores.add(rs.getString("marca"));

						Element sponsor = doc.createElement("sponsor");
						sponsor.setAttribute("categoria", rs.getString("tipo_patrocinio"));

						Element nombre = doc.createElement("nombre");
						nombre.appendChild(doc.createTextNode(rs.getString("marca")));
						sponsor.appendChild(nombre);

						Element inversor = doc.createElement("inversor");
						inversor.appendChild(doc.createTextNode(String.valueOf(rs.getFloat("aportacion_economica"))));
						sponsor.appendChild(inversor);

						partners.appendChild(sponsor);
					}
				}
				
				doc.getDocumentElement().appendChild(lista);
				doc.getDocumentElement().appendChild(partners);
				if (!valor) {
					System.out.println("No se encontro ningun registro");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al ejecutar la consulta, Detalle del error:" + e.getMessage());
		}
		return doc;
	}
}

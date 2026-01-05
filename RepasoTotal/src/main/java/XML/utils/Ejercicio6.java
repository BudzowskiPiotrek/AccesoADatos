package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Ejercicio6 {
	ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_BD = "practica_esports";

	public boolean enviarLeidoSQL(Document doc) {
		String sqlEvento = "INSERT INTO torneos (id_torneo, nombre, videojuego) VALUES (?, ?, ?)";
        String sqlEquipo = "INSERT INTO equipos (nombre_tag, pais, ranking_mundial, id_torneo) VALUES (?, ?, ?, ?)";
        String sqlSponsor = "INSERT INTO patrocinadores (marca, tipo_patrocinio, aportacion_economica, id_torneo) VALUES (?, ?, ?, ?)";
		try (Connection conn = conSQL.conectar(NOMBRE_BD)) {
			doc.getDocumentElement().normalize();

			String idEvento = doc.getDocumentElement().getAttribute("id");
			String juego = doc.getDocumentElement().getAttribute("juego");

			Element infoG = (Element) doc.getElementsByTagName("info_general").item(0);
			String nombreEvento = infoG.getElementsByTagName("nombre").item(0).getTextContent();

			try (PreparedStatement psEvento = conn.prepareStatement(sqlEvento)) {
				psEvento.setString(1, idEvento);
				psEvento.setString(2, nombreEvento);
				psEvento.setString(3, juego);
				psEvento.executeUpdate();
			} catch (SQLException e) {
				System.err.println("Error al intentar volcar datos de Evento, detalle del error: " + e.getMessage());
			}

			NodeList listaEquipos = doc.getElementsByTagName("equipo");
			
			try (PreparedStatement psEquipo = conn.prepareStatement(sqlEquipo)) {
				for (int i = 0; i < listaEquipos.getLength(); i++) {
					Element eq = (Element) listaEquipos.item(i);
					psEquipo.setString(1, eq.getElementsByTagName("nombre").item(0).getTextContent());
					psEquipo.setString(2, eq.getElementsByTagName("region").item(0).getTextContent());
					psEquipo.setInt(3, Integer.parseInt(eq.getAttribute("ranking")));
					psEquipo.setString(4, idEvento);
					psEquipo.executeUpdate();
				}
			} catch (SQLException e) {
				System.err.println("Error al intentar volcar datos de Equipo, detalle del error:" + e.getMessage());
			}

			NodeList listaSponsors = doc.getElementsByTagName("sponsor");
			
			try (PreparedStatement psSponsor = conn.prepareStatement(sqlSponsor)) {
				for (int i = 0; i < listaSponsors.getLength(); i++) {
	                Element sp = (Element) listaSponsors.item(i);
	                psSponsor.setString(1, sp.getElementsByTagName("nombre").item(0).getTextContent());
	                psSponsor.setString(2, sp.getAttribute("categoria"));
	                psSponsor.setDouble(3, Double.parseDouble(sp.getElementsByTagName("inversor").item(0).getTextContent()));
	                psSponsor.setString(4, idEvento);
	                psSponsor.executeUpdate();
	            }
			} catch (SQLException e) {
				System.err.println("Error al intentar volcar datos de Patrocinador, detalle del error:" + e.getMessage());
			}
			return true;
		} catch (SQLException e) {
			System.err.println("Error de SQL: " + e.getMessage());
			return false;
		}
	}
}

package exa1acda2526.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.neodatis.odb.ODB;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import exa1acda2526.Estrellas;
import exa1acda2526.Galaxias;
import exa1acda2526.Planetas;

public class LogicaEjercicios {
	private ConnectionNeodatis conNeo = new ConnectionNeodatis();
	private ConnectionSQL conSQL = new ConnectionSQL();

	public void cuantasEstrellasPorTipo() {
		String sql = "SELECT g.tipo, COUNT(*) FROM galaxias g left JOIN estrellas e on g.nombre=e.nombre_galaxia GROUP BY g.tipo;";
		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Tipo: " + rs.getString(1) + " | Cantidad : " + rs.getString(2));
			}

		} catch (SQLException e) {
			System.err.println("Error al intentar Obtener Consulta en Sql");
			System.err.println("Detalle del error " + e.getMessage());
		}
	}

	public void crearPlanetas() {
		ODB odb = conNeo.abrir();
		String sql = "SELECT p.nombre, p.masa, p.periodo_orbital, p.nombre_estrella_orbita, p.descubridor, p.fecha_descubrimiento FROM planetas P";
		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Planetas p1 = new Planetas(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
				meterEstrellas(rs.getString(4), p1);
				odb.store(p1);
				odb.commit();
				
				
			}
			odb.close();
			System.out.println("Exito se creo Base Datos neodatis");
		} catch (SQLException e) {
			System.err.println("Error al intentar crear base datos paarte de planetas");
			System.err.println("Detalle del error " + e.getMessage());
		}
	}

	private void meterEstrellas(String str, Planetas p1) {
		String sql = "SELECT E.nombre, E.coord_x, E.coord_y, E.coord_z, E.distancia, E.tipo, E.nombre_galaxia, E.fecha_descubrimiento, E.descubridor"
				+ " FROM galaxias G LEFT JOIN estrellas E ON G.nombre=E.nombre_galaxia WHERE E.nombre = ? ;";

		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Estrellas estrella = new Estrellas(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4),
						rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9));
				p1.getListaEstrellas().add(estrella);
			}
		} catch (SQLException e) {
			System.err.println("Error al intentar Obtener Consulta en Sql");
			System.err.println("Detalle del error " + e.getMessage());
		}
		
	}

	public void crearGalaxias() {
		ODB odb = conNeo.abrir();
		String sql = "SELECT nombre, tipo, numero_estrellas FROM galaxias ;";

		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Galaxias g1 = new Galaxias(rs.getString(1), rs.getString(2), rs.getLong(3));
				meterEstrellas(rs.getString(1), g1);
				odb.store(g1);
				odb.commit();
				
			}
			odb.close();
		} catch (SQLException e) {
			System.err.println("Error al intentar crear base datos paarte de galaxias");
			System.err.println("Detalle del error " + e.getMessage());
		}
	}

	private void meterEstrellas(String str, Galaxias g1) {
		String sql = "SELECT E.nombre, E.coord_x, E.coord_y, E.coord_z, E.distancia, E.tipo, E.nombre_galaxia, E.fecha_descubrimiento, E.descubridor"
				+ " FROM galaxias G LEFT JOIN estrellas E ON G.nombre=E.nombre_galaxia WHERE E.nombre_galaxia = ? ;";

		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Estrellas estrella = new Estrellas(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4),
						rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getInt(9));
				g1.getListaEstrellas().add(estrella);
			}
		} catch (SQLException e) {
			System.err.println("Error al intentar Obtener Consulta en Sql");
			System.err.println("Detalle del error " + e.getMessage());
		}
	}

	public Document crearFicheroXML(Document doc, String str) {
		String sql = "SELECT nombre, tipo, numero_estrellas FROM galaxias WHERE  nombre =  ? ;";
		Element galaxia = doc.createElement("Galaxia");
		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Element nombre = doc.createElement("Nombre");
				Text textoNombre = doc.createTextNode(rs.getString(1));
				nombre.appendChild(textoNombre);
				galaxia.appendChild(nombre);

				Element tipo = doc.createElement("Tipo");
				Text textoTipo = doc.createTextNode(rs.getString(2));
				tipo.appendChild(textoTipo);
				galaxia.appendChild(tipo);

				Element numEstrellas = doc.createElement("NumeroEstrellas");
				Text textoNumEstrellas = doc.createTextNode(rs.getString(3));
				numEstrellas.appendChild(textoNumEstrellas);
				galaxia.appendChild(numEstrellas);

				crearNodoEstrella(galaxia, doc, str);
			}

		} catch (SQLException e) {
			System.err.println("Error al intentar Obtener Consulta en Sql");
			System.err.println("Detalle del error " + e.getMessage());
		}
		doc.getDocumentElement().appendChild(galaxia);
		return doc;
	}

	private void crearNodoEstrella(Element galaxia, Document doc, String str) {
		String sql = "SELECT E.nombre, E.coord_x, E.coord_y, E.coord_z, E.distancia, E.tipo, E.fecha_descubrimiento, E.descubridor"
				+ " FROM galaxias G LEFT JOIN estrellas E ON G.nombre=E.nombre_galaxia WHERE G.nombre = ? ;";
		Element estrella = doc.createElement("Estrella");
		try (Connection con = conSQL.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, str);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				estrella.setAttribute("nombre", rs.getString(1));
				Element cordenadas = doc.createElement("Cordenadas");

				Element corX = doc.createElement("X");
				Text textocorX = doc.createTextNode(rs.getString(2));
				corX.appendChild(textocorX);
				cordenadas.appendChild(corX);

				Element corY = doc.createElement("Y");
				Text textocorY = doc.createTextNode(rs.getString(3));
				corY.appendChild(textocorY);
				cordenadas.appendChild(corY);

				Element corZ = doc.createElement("X");
				Text textocorZ = doc.createTextNode(rs.getString(4));
				corZ.appendChild(textocorZ);
				cordenadas.appendChild(corZ);

				estrella.appendChild(cordenadas);

				Element distancia = doc.createElement("Distancia");
				Text textodistancia = doc.createTextNode(rs.getString(5));
				distancia.appendChild(textodistancia);
				estrella.appendChild(distancia);

				Element tipo = doc.createElement("Tipo");
				Text textoTipo = doc.createTextNode(rs.getString(6));
				tipo.appendChild(textoTipo);
				estrella.appendChild(tipo);

				Element fecha = doc.createElement("fechaDescubrimineto");
				Text textofecha = doc.createTextNode(rs.getString(7));
				fecha.appendChild(textofecha);
				estrella.appendChild(fecha);

				Element descubridor = doc.createElement("descubridor");
				Text textoDescubridor = doc.createTextNode(rs.getString(8));
				descubridor.appendChild(textoDescubridor);
				estrella.appendChild(descubridor);
			}
			galaxia.appendChild(estrella);
		} catch (SQLException e) {
			System.err.println("Error Inesperado en segunda consulta");
			System.err.println("Detalle del error " + e.getMessage());
		}
	}

}

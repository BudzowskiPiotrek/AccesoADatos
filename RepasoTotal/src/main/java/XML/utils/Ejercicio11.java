package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio11 {
	ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_DB = "practica_astronomia2";

	public Document convertirXML(Document doc, String condicion) {
		String sql = "SELECT g.nombre as nom_g, g.tipo, g.numero_estrellas, e.nombre as nom_e, e.coord_x, e.coord_y, e.coord_z, e.distancia, e.tipo as tipo_e, e.fecha_descubrimiento, e.nombre_estrella_principal, d.nombre as nom_d "
				+ "FROM galaxias g LEFT JOIN estrellas e ON g.nombre=e.nombre_galaxia LEFT JOIN descubridores d ON e.descubridor=d.carnet_investigador WHERE g.nombre = ?;";
		
		Element root = doc.getDocumentElement();
		Element estrellas = null;
		try (Connection con = conSQL.conectar(NOMBRE_DB); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, condicion);
			try (ResultSet rs = ps.executeQuery()) {
				int control = 0;
				while (rs.next()) {
					if (control == 0) {
						Element nombreGa = doc.createElement("Nombre");
						nombreGa.appendChild(doc.createTextNode(rs.getString("nom_g")));
						root.appendChild(nombreGa);

						Element tipo = doc.createElement("Tipo");
						tipo.appendChild(doc.createTextNode(rs.getString("tipo")));
						root.appendChild(tipo);

						Element numero = doc.createElement("NumeroEstrellas");
						numero.appendChild(doc.createTextNode(String.valueOf(rs.getDouble("numero_estrellas"))));
						root.appendChild(numero);

						estrellas = doc.createElement("Estrellas");
						root.appendChild(estrellas);
						control++;
					}

					Element estrella = doc.createElement("Estrella");
					estrella.setAttribute("nombre", rs.getString("nom_e"));
					estrellas.appendChild(estrella);

					Element cordenadas = doc.createElement("Cordenadas");
					estrella.appendChild(cordenadas);

					Element x = doc.createElement("X");
					x.appendChild(doc.createTextNode(rs.getString("coord_x")));
					cordenadas.appendChild(x);

					Element y = doc.createElement("Y");
					y.appendChild(doc.createTextNode(rs.getString("coord_y")));
					cordenadas.appendChild(y);

					Element z = doc.createElement("Z");
					z.appendChild(doc.createTextNode(rs.getString("coord_z")));
					cordenadas.appendChild(z);

					Element distancia = doc.createElement("Distancia");
					distancia.appendChild(doc.createTextNode(String.valueOf(rs.getFloat("distancia"))));
					estrella.appendChild(distancia);

					String estrellaPrincipal = rs.getString("nombre_estrella_principal");
					if (estrellaPrincipal != null) {
						Element principal = doc.createElement("EstrellaPrincipal");
						principal.appendChild(doc.createTextNode(estrellaPrincipal));
						estrella.appendChild(principal);
					}

					Element tipoEs = doc.createElement("Tipo");
					tipoEs.appendChild(doc.createTextNode(rs.getString("tipo_e")));
					estrella.appendChild(tipoEs);

					Element fecha = doc.createElement("FechaDescubrimiento");
					fecha.appendChild(doc.createTextNode(String.valueOf(rs.getDate("fecha_descubrimiento"))));
					estrella.appendChild(fecha);

					Element descubridor = doc.createElement("Descubridor");
					descubridor.appendChild(doc.createTextNode(rs.getString("nom_d")));
					estrella.appendChild(descubridor);
				}
			}
		} catch (SQLException e) {
			System.err.println("ERROR al intentar ejecutar consulta, detalle del error: " + e.getMessage());
		}
		return doc;
	}
}

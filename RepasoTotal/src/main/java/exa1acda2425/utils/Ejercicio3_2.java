package exa1acda2425.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.Document;

public class Ejercicio3_2 {
	private ConnectionSQL conSQL = new ConnectionSQL();

	public boolean insertarDesdeXML(Document doc) {
		Connection con = null;
		boolean exito = false;

		try {
			con = conSQL.conectar();

			NodeList vuelosNodeList = doc.getElementsByTagName("vuelo");

			for (int i = 0; i < vuelosNodeList.getLength(); i++) {
				Node vueloNode = vuelosNodeList.item(i);

				if (vueloNode.getNodeType() == Node.ELEMENT_NODE) {
					Element vueloElement = (Element) vueloNode;
					String identificador = vueloElement.getAttribute("identificador");
					insertarVuelo(con, vueloElement, identificador);

					NodeList tripulacionNodes = vueloElement.getElementsByTagName("tripulacion");
					if (tripulacionNodes.getLength() > 0) {
						Element tripulacionElement = (Element) tripulacionNodes.item(0);
						insertarTripulacion(con, tripulacionElement, identificador);
					}
					NodeList pasajeNodes = vueloElement.getElementsByTagName("pasaje");
					if (pasajeNodes.getLength() > 0) {
						Element pasajeElement = (Element) pasajeNodes.item(0);
						insertarPasajes(con, pasajeElement, identificador);
					}
				}
			}

			con.commit();
			System.out.println("Todos los datos del XML han sido insertados");

		} catch (Exception e) {
			System.err.println("ERROR: Fallo en el proceso de insertar SQL");
			System.err.println("Detalle: " + e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("Error al cerrar conexión: " + e.getMessage());
				}
			}
		}
		return true;
	}

	private void insertarPasajes(Connection con, Element pasajeElement, String identificador) {
		// TODO Auto-generated method stub
		
	}

	private void insertarTripulacion(Connection con, Element tripulacionElement, String identificador) {
		// TODO Auto-generated method stub
		
	}

	private void insertarVuelo(Connection con, Element vueloElement, String identificador) {
		// TODO Auto-generated method stub
		
	}

}

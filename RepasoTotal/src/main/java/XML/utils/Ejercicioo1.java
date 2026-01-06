package XML.utils;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XML.models.Dnd_campana;
import XML.models.Dnd_objeto;
import XML.models.Dnd_personaje;

public class Ejercicioo1 {
	private ConnectionNeodatis conNeo = new ConnectionNeodatis();
	private final String NOMBRE_BD = "universo_dnd.odb";

	public boolean crearNeodatis(Document doc) {
		ODB con = null;
		Dnd_campana campana = null;
		Dnd_personaje pj = null;
		Dnd_objeto objeto = null;
		try {
			con = conNeo.abrir(NOMBRE_BD);
			
			NodeList listaCampana = doc.getElementsByTagName("campaña");
			for (int i = 0; i < listaCampana.getLength(); i++) {
				Element ca = (Element) listaCampana.item(i);
				String nombreCa = ca.getAttribute("nombre");
				String dm = ca.getAttribute("dm");
				campana = new Dnd_campana(nombreCa, dm);
				
				NodeList listaPjs = ca.getElementsByTagName("personaje");
				for (int j = 0; j < listaPjs.getLength(); j++) {
					Element pe = (Element) listaPjs.item(j);
					String nombrePe = pe.getAttribute("nombre");
					pj = new Dnd_personaje(nombrePe);
					
					NodeList listaObs = pe.getElementsByTagName("objeto");
					for (int k = 0; k < listaObs.getLength(); k++) {
						Element ob = (Element) listaObs.item(k);
						String rareza= ob.getAttribute("rareza");
						String nombreOb = ob.getTextContent();
						objeto = new Dnd_objeto(nombreOb, rareza);
						pj.getLista().add(objeto);
					}
					campana.getLista().add(pj);
				}
				con.store(campana);
			}
			con.commit();
			return true;
		} catch (ODBRuntimeException e) {
			System.out.println("Error al intentar volcar los datos, detalle del error: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (ODBRuntimeException e) {
				System.out
						.println("Error al intentar cerrar conexion de neodatis, detalle del error: " + e.getMessage());
			}
		}
		return false;
	}
}

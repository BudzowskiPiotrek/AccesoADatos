package XML.utils;

import java.sql.Date;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XML.models.VetDuenos;
import XML.models.VetPacientes;
import XML.models.VetVisita;

public class Ejercicio08 {
	private ConnectionNeodatis conNeo = new ConnectionNeodatis();
	private final String NOMBRE_BD = "clinica.odb";

	public boolean CrearNeodatis(Document doc) {
		ODB con = null;
		try {
			con = conNeo.abrir(NOMBRE_BD);

			Element dueno = (Element) doc.getElementsByTagName("dueño").item(0);
			VetDuenos d1 = new VetDuenos(dueno.getAttribute("nombre"));

			NodeList listaMascotas = doc.getElementsByTagName("mascota");

			for (int i = 0; i < listaMascotas.getLength(); i++) {
				Element ma = (Element) listaMascotas.item(i);
				String nombreMascota = ma.getAttribute("nombre");
				String especie = ma.getAttribute("especie");
				VetPacientes p1 = new VetPacientes(nombreMascota, especie);
				d1.getListaMascotas().add(p1);

				NodeList listaVisitas = ma.getElementsByTagName("visita");

				for (int j = 0; j < listaVisitas.getLength(); j++) {
					Element vi = (Element) listaVisitas.item(j);
					Date fecha = Date.valueOf(vi.getAttribute("fecha"));
					String diagnostico = vi.getTextContent();
					double precio = Double.valueOf(vi.getAttribute("precio"));
					p1.getListaVisitas().add(new VetVisita(fecha, diagnostico, precio));
				}
			}
			con.store(d1);
			con.commit();
			return true;
		} catch (ODBRuntimeException e) {
			System.out.println("Error al intentar ejecutar la consulta.");
			System.out.println("Detalle de error: " + e.getMessage());
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error al intentar cerrar la conexion. Detalle de error: " + e2.getMessage());
				}
		}
		return false;
	}
}

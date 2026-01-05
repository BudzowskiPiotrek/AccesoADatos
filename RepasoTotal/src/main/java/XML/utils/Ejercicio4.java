package XML.utils;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import XML.models.Galaxia;
import XML.models.Estrella;
import XML.models.Planeta;

public class Ejercicio4 {

	ConnectionNeodatis conNeo = new ConnectionNeodatis();

	public Document crearDocumentoXML(Document doc, String condicion) {
		ODB odb = null;
		try {
			odb = conNeo.abrir("universo.odb");
			ICriterion c1 = Where.equal("nombre", condicion);
			IQuery q = new CriteriaQuery(Galaxia.class, c1);
			Objects<Galaxia> obj = odb.getObjects(q);
			
			if (obj.isEmpty()) {
				System.out.println("Consulta : 0 Resultados");
				System.out.println("No se encontro : " + condicion + " en la base de datos");
			} else {
				Galaxia g = obj.getFirst();
				doc.getDocumentElement().setAttribute("galaxia", g.getNombre());
				doc.getDocumentElement().setAttribute("tipo", g.getTipo());

				Element listado = doc.createElement("listado_estrellas");
				doc.getDocumentElement().appendChild(listado);
				
				for (Estrella e : g.getEstrellas()) {
					Element estrella = doc.createElement("estrella");
					estrella.setAttribute("nombre", e.getNombre());
					listado.appendChild(estrella);

					Element planetas = doc.createElement("planetas");
					estrella.appendChild(planetas);

					for (Planeta p : e.getPlanetas()) {
						Element planeta = doc.createElement("planeta");
						planeta.setAttribute("habitable", p.isHabitable() ? "si" : "no");
						planeta.appendChild(doc.createTextNode(p.getNombre()));
						planetas.appendChild(planeta);
					}
				}
			}

		} catch (ODBRuntimeException e) {
			System.out.println("Error al intentar ejecutar la consulta.");
			System.out.println("Detalle de error: " + e.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error al intentar cerrar la conexion. Detalle de error: " + e2.getMessage());
				}
			}
		}
		return doc;
	}
}

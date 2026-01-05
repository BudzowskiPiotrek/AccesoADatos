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

public class Ejercicio5 {

	ConnectionNeodatis conNeo = new ConnectionNeodatis();

	public Document crearDocumentoXML(Document doc, String condicion) {
		ODB odb = null;
		try {
			odb = conNeo.abrir("universo.odb");
			ICriterion c1 = Where.equal("nombre", condicion);
			IQuery q = new CriteriaQuery(Galaxia.class, c1);
			Objects<Galaxia> obj = odb.getObjects(q);

			if (obj.isEmpty()) {
				System.out.println("No se encontró la galaxia: " + condicion);
			} else {
				Galaxia g = obj.getFirst();
				doc.getDocumentElement().setAttribute("galaxia", g.getNombre());
				doc.getDocumentElement().setAttribute("filtro", "Solo habitables");

				Element listado = doc.createElement("listado_estrellas_habitables");
				doc.getDocumentElement().appendChild(listado);

				for (Estrella e : g.getEstrellas()) {
					boolean habitable = false;
					for (Planeta p : e.getPlanetas()) {
						if (p.isHabitable()) {
							habitable = true;
							break;
						}
					}

					if (habitable) {
						Element estrella = doc.createElement("estrella");
						estrella.setAttribute("nombre", e.getNombre());
						listado.appendChild(estrella);

						Element planetasNode = doc.createElement("planetas");
						estrella.appendChild(planetasNode);

						for (Planeta p : e.getPlanetas()) {
							if (p.isHabitable()) {
								Element planeta = doc.createElement("planeta");
								planeta.setAttribute("habitable", "si");
								planeta.appendChild(doc.createTextNode(p.getNombre()));
								planetasNode.appendChild(planeta);
							}
						}
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

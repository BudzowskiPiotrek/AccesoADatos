package XML.models;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class CrearArchivoNeodatis {
	public static void main(String[] args) {

		UniGalaxia g1 = new UniGalaxia("Vía Láctea", "Espiral");

		UniEstrella e1 = new UniEstrella("Sol");
		e1.addPlaneta(new UniPlaneta("Mercurio", false));
		e1.addPlaneta(new UniPlaneta("Venus", false));
		e1.addPlaneta(new UniPlaneta("Tierra", true));
		e1.addPlaneta(new UniPlaneta("Marte", false));
		e1.addPlaneta(new UniPlaneta("Júpiter", false));

		UniEstrella e2 = new UniEstrella("Próxima Centauri");
		e2.addPlaneta(new UniPlaneta("Próxima b", true));
		e2.addPlaneta(new UniPlaneta("Próxima c", false));

		g1.addEstrella(e1);
		g1.addEstrella(e2);

		UniGalaxia g2 = new UniGalaxia("Andrómeda", "Espiral");

		UniEstrella e3 = new UniEstrella("Alpheratz");
		e3.addPlaneta(new UniPlaneta("Sirius Prime", false));
		e3.addPlaneta(new UniPlaneta("Magrathea", true));

		UniEstrella e4 = new UniEstrella("Mirach");
		e4.addPlaneta(new UniPlaneta("Mirach III", false));

		g2.addEstrella(e3);
		g2.addEstrella(e4);

		UniGalaxia g3 = new UniGalaxia("Sombrero", "Lenticular");

		UniEstrella e5 = new UniEstrella("Estrella Desconocida X");
		e5.addPlaneta(new UniPlaneta("Planeta Fantasma", false));

		g3.addEstrella(e5);

		ODB odb = null;
		try {
			odb = ODBFactory.open("D:\\xml\\universo.odb");
			odb.store(g1);
			odb.store(g2);
			odb.store(g3);
			System.out.println("Base de Datos Neodatis poblada con 3 galaxias y múltiples sistemas.");
		} catch (Exception e) {
			System.err.println("Error al guardar: " + e.getMessage());
		} finally {
			if (odb != null)
				odb.close();
		}

	}
}

package XML.models;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class CrearArchivoNeodatis {
	public static void main(String[] args) {

		Galaxia g1 = new Galaxia("Vía Láctea", "Espiral");

		Estrella e1 = new Estrella("Sol");
		e1.addPlaneta(new Planeta("Mercurio", false));
		e1.addPlaneta(new Planeta("Venus", false));
		e1.addPlaneta(new Planeta("Tierra", true));
		e1.addPlaneta(new Planeta("Marte", false));
		e1.addPlaneta(new Planeta("Júpiter", false));

		Estrella e2 = new Estrella("Próxima Centauri");
		e2.addPlaneta(new Planeta("Próxima b", true));
		e2.addPlaneta(new Planeta("Próxima c", false));

		g1.addEstrella(e1);
		g1.addEstrella(e2);

		Galaxia g2 = new Galaxia("Andrómeda", "Espiral");

		Estrella e3 = new Estrella("Alpheratz");
		e3.addPlaneta(new Planeta("Sirius Prime", false));
		e3.addPlaneta(new Planeta("Magrathea", true));

		Estrella e4 = new Estrella("Mirach");
		e4.addPlaneta(new Planeta("Mirach III", false));

		g2.addEstrella(e3);
		g2.addEstrella(e4);

		Galaxia g3 = new Galaxia("Sombrero", "Lenticular");

		Estrella e5 = new Estrella("Estrella Desconocida X");
		e5.addPlaneta(new Planeta("Planeta Fantasma", false));

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

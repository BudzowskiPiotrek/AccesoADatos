package practica01;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ElLeerDatos {
	final static String BDPer = "D:/Persona.yap";

	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(BDPer);
		// LEER TODOS LOS REGISTROS
		System.out.println("Todas las personas son:");
		Persona per = new Persona(null, null);
		ObjectSet<Persona> result = db.queryByExample(per);
		if (result.size() == 0)
			System.out.println("No existen Registros de Personas..");
		else {
			System.out.println("Número de registros: " + result.size());

			while (result.hasNext()) {
				Persona p = result.next();
				System.out.println("Nombre: " + p.getNombre() + ",Ciudad:" + p.getCiudad());
			}
		}
		// LEER SÓLO LOS REGISROS DE AQUELLOS LLAMADOS Enrique
		System.out.println("Todos los Enrique son...");
		Persona perEnrique = new Persona("Enrique", null);
		ObjectSet<Persona> resultEnrique = db.queryByExample(perEnrique);
		if (resultEnrique.size() == 0)
			System.out.println("No existen Registros de Personas..");
		else {
			System.out.println("Número de registros: " + resultEnrique.size());

			while (resultEnrique.hasNext()) {
				Persona p = resultEnrique.next();
				System.out.println("Nombre: " + p.getNombre() + ",Ciudad:" + p.getCiudad());
			}
		}
		// LEER SÓLO LOS REGISTROS DE AQUELLAS PERSONAS QUE VIVAN EN Madrid
		System.out.println("En Madrid viven...");
		Persona perMadrid = new Persona(null, "Madrid");
		ObjectSet<Persona> resultMadrid = db.queryByExample(perMadrid);
		if (resultMadrid.size() == 0)
			System.out.println("No existen Registrosde Personas..");
		else {
			System.out.println("Número de registros: " + resultMadrid.size());

			while (resultMadrid.hasNext()) {
				Persona p = resultMadrid.next();
				System.out.println("Nombre: " + p.getNombre() + ",Ciudad:" + p.getCiudad());
			}
		}
		// CIERRA LA BASE DE DATOS
		db.close();
	}
}

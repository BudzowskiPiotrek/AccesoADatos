package practica01;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class ElModificarDatos {
	final static String BDPer = "D:/Persona.yap";

	public static void main(String[] args) {
		ObjectContainer db = Db4oEmbedded.openFile(BDPer);
		// CAMBIAR LA CIUDAD DONDE VIVE JUAN POR TOLEDO
		Persona perJuan = new Persona("Juan", null);
		ObjectSet<Persona> resultJuan = db.queryByExample(perJuan);
		if (resultJuan.size() == 0)
			System.out.println("No existen Juan...");
		else {
			Persona existe = (Persona) resultJuan.next();
			existe.setCiudad("Toledo");
			db.store(existe); // ciudad modificada
			// consultar los datos
			ObjectSet<Persona> result = db.queryByExample(new Persona(null, null));
			while (result.hasNext()) {
				Persona p = result.next();
				System.out.println("Nombre: " + p.getNombre() + ",Ciudad:" + p.getCiudad());
			}
		}
		// CIERRA LA BASE DE DATOS
		db.close();
	}
}

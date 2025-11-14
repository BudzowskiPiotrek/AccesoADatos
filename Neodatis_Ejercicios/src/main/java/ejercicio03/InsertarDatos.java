package ejercicio03;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class InsertarDatos {

	public static void main(String[] args) {
		ODB odb = null;
		final String NOMBRE_BD = "EQUIPOS.DB";

		try {
			odb = ODBFactory.open(NOMBRE_BD);
			System.out.println("Base de datos " + NOMBRE_BD + " abierta o creada correctamente.");

			// ingresarDatos(odb);

			verConsulta(odb);

		} catch (Exception e) {
			System.err.println("Error al operar con la base de datos: " + e.getMessage());
			e.printStackTrace();
		} finally {
			odb.close();
		}
	}

	private static void verConsulta(ODB odb) {
		IQuery query = new CriteriaQuery(Deportes.class);
		Objects<Deportes> deportes = odb.getObjects(query);

		while (deportes.hasNext()) {
			Deportes d = deportes.next();
			System.out.println("Deporte: " + d.getNombre());

			for (Jugadores j : d.getJugadores()) {
				System.out.println("Jugador: " + j.getNombre() + ", País: " + j.getPais().getNombrepais());
			}
		}
	}

	private static void ingresarDatos(ODB odb) {
		Paises p1 = new Paises(1, "España");
		Paises p2 = new Paises(2, "Alemania");
		Paises p3 = new Paises(3, "Francia");

		Jugadores j1 = new Jugadores("Lamine Yamal", p1);
		Jugadores j2 = new Jugadores("Pedri Pedri", p1);
		Jugadores j3 = new Jugadores("Adolf Müller", p2);
		Jugadores j4 = new Jugadores("Lena Stalin", p2);
		Jugadores j5 = new Jugadores("Antoine Moroco", p3);

		Deportes futbol = new Deportes("Futbol");
		futbol.anadirJugador(j1);
		futbol.anadirJugador(j2);
		futbol.anadirJugador(j3);
		Deportes tenis = new Deportes("Tenis");
		tenis.anadirJugador(j4);
		tenis.anadirJugador(j5);

		odb.store(p1);
		odb.store(p2);
		odb.store(p3);

		odb.store(j1);
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(j5);

		odb.store(futbol);
		odb.store(tenis);

		System.out.println("Datos insertados correctamente.");

		odb.commit();
	}
}
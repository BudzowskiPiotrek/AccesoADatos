package ejercicio02;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class InsertarDatos {

	public static void main(String[] args) {
		ODB odb = null;
		final String NOMBRE_BD = "EQUIPOS.DB";

		try {
			odb = ODBFactory.open(NOMBRE_BD);
			System.out.println("Base de datos " + NOMBRE_BD + " abierta o creada correctamente.");

			//ingresarDatos(odb);
			
			verPaises(odb);
			
			verJugadores(odb);

		} catch (Exception e) {
			System.err.println("Error al operar con la base de datos: " + e.getMessage());
			e.printStackTrace();
		} finally {
			odb.close();
		}
	}

	private static void verJugadores(ODB odb) {
		Objects<Jugadores> jugadoresResult = odb.getObjects(Jugadores.class);
		while (jugadoresResult.hasNext()) {
			Jugadores j = jugadoresResult.next();
			System.out.println(j.toString());
		}
	}

	private static void verPaises(ODB odb) {
		Objects<Paises> paisesResult = odb.getObjects(Paises.class);
		while (paisesResult.hasNext()) {
			Paises p = paisesResult.next();
			System.out.println("ID: " + p.getId() + ", Nombre: " + p.toString());
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

		odb.store(p1);
		odb.store(p2);
		odb.store(p3);

		odb.store(j1);
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(j5);

		System.out.println("Datos insertados correctamente.");

		odb.commit();
	}
}
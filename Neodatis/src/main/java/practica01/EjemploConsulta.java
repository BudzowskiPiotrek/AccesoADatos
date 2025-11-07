package practica01;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class EjemploConsulta {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("C:/Users/Wilku/Documents/GitHub/AccesoADatos/Neodatis/neodatis.test"); // Abrir BD
		IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("ciudad", "Madrid"));
		query.orderByAsc("nombre,edad"); // ordena ascendemente por nombre y edad
		
		// recuperamos todos los objetos
		Objects<Jugadores> objects = odb.getObjects(query);
		System.out.printf("%d Jugadores: %n", objects.size());
		int i = 1;
		
		// visualizar los objetos
		while (objects.hasNext()) {
			Jugadores jug = objects.next();
			System.out.printf("%d: %s, %s, %s %n", i++, jug.getNombre(), jug.getDeporte(), jug.getCiudad(),
					jug.getEdad());
		}
		
		// MODIFICAR
		IQuery query2 = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Maria"));
		Objects<Jugadores> objects2 = odb.getObjects(query2);
		// Obtiene sólo el primer objeto encontrado.
		Jugadores jug = (Jugadores) objects2.getFirst();

		jug.setDeporte("voley-playa"); // Cambia el deporte
		odb.store(jug); // actualiza el objeto.
		odb.close(); // Cerrar BD
	}
}
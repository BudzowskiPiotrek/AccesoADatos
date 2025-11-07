package practica01;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

public class EjemploOID {
	public static void main(String[] args) {
		ODB odb = ODBFactory.open("C:/Users/Wilku/Documents/GitHub/AccesoADatos/Neodatis/neodatis.test");
		OID oid = OIDFactory.buildObjectOID(3);

		Jugadores jug = (Jugadores) odb.getObjectFromId(oid);
		System.out.println(jug.getNombre() + "*" + jug.getDeporte() + "*" + jug.getCiudad() + "*" + jug.getEdad());
		odb.close(); // Cierro la BD
	}
}

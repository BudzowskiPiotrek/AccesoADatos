package exa1acda2526.utils;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class ConnectionNeodatis {
	private final String RUTA = "D://exa1acda2526//astronomia.neo";

	public ODB abrir() {
		ODB odb = null;
		try {
			odb = ODBFactory.open(RUTA);
		} catch (ODBRuntimeException e) {
			System.err.println("Error al intentar establecer la conexion");
			System.err.println("Detalle del error " + e.getMessage());
		}
		return odb;
	}
}

package XML.utils;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class ConnectionNeodatis {
	private final String RUTA = "D://xml//";

	public ODB abrir(String nombre) {
		ODB odb = null;
		try {
			odb = ODBFactory.open(RUTA + nombre);
		} catch (ODBRuntimeException e) {
			System.err.println("Error al intentar establecer la conexion");
			System.err.println("Detalle del error " + e.getMessage());
		}
		return odb;
	}

}

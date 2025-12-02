package exa1acda2425.utils;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class ConnectionNeodatis {
private final String RUTADOS = "D:\\vuelos.neo";
	
	public ODB openDB() throws ODBRuntimeException {
		return ODBFactory.open(RUTADOS);
	}

}

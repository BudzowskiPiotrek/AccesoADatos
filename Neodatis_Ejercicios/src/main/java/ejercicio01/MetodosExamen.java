package ejercicio01;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.ODBRuntimeException;

public class MetodosExamen {

	private static final String DB_NAME = "tutusrutu.neodatis";
	private Scanner sn = new Scanner(System.in);

	private ODB openDB() throws ODBRuntimeException {
		return ODBFactory.open(DB_NAME);
	}

	public int obtenerNumero(String str) {
		while (true) {
			try {
				System.out.println(str);
				int n = sn.nextInt();
				sn.nextLine();
				return n;
			} catch (InputMismatchException e) {
				System.err.println("*** Solo numeros enteros ***");
				sn.nextLine();
			}
		}
	}

	public void listar() {
		ODB odb = null;
		try {
			odb = openDB();
			ICriterion c1 = Where.equal("argumento", "argumentodos");
			IQuery q = new CriteriaQuery(Empleados.class, c1);

			Objects<Empleados> obj = odb.getObjects(q);

			if (obj.isEmpty()) {
				System.out.println("Consulta 0 Resultados");
				System.out.println("No encontrados por ejemplo empleados en base datos");
			} else {
				System.out.println("Consultas Encontradas :" + obj.size());
				for (Empleados e : obj) {
					System.out.println(e);
				}
			}
		} catch (ODBRuntimeException e) {
			System.err.println("Error --tipo-- de: ");
			System.err.println("Detalle de error" + e.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e) {
					System.err.println("Error al intentar cerrar.\nDetalle de error" + e.getMessage());
				}
			}
		}
	}

	public boolean insertar(Empleados e) {
		ODB odb = null;
		try {
			odb = openDB();
			odb.store(e);
			System.out.println("Exito, se ha insertado a" + e);
			return true;
		} catch (ODBRuntimeException e1) {
			System.err.println("Error --tipo-- de: ");
			System.err.println("Detalle de error" + e1.getMessage());
			return false;
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e1) {
					System.err.println("Error al intentar cerrar.\nDetalle de error" + e1.getMessage());
				}
			}
		}
	}
}

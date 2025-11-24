package peval3acda2526;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class MetodosNeodatis {
	private static final String BD = "D:\\pval3acda2526\\sjcucina.neo";

	private ODB openBD() throws ODBRuntimeException {
		return ODBFactory.open(BD);
	}

	public boolean creacion() {
		ODB odb = null;
		try {
			odb = openBD();

			PerfilSabor p1 = new PerfilSabor("Marroqui", "Cítrico, floral, amaderado",
					"Cuscús, pollo, tagines de verduras", false);
			PerfilSabor p2 = new PerfilSabor("India", "Terroso, cálido, ligeramente picante ",
					"Lentejas (dal), arroz, pollo tikka ", true);
			PerfilSabor p3 = new PerfilSabor("Europea", "Dulce, especiado, a nuez", "Tartas de manzana, galletas, café",
					false);
			PerfilSabor p4 = new PerfilSabor("Mediterránea", "Anisado, fresco, herbal",
					"Pescados blancos, mariscos, sopas", false);
			PerfilSabor p5 = new PerfilSabor("Mexicana", "Picante, ahumado, robusto",
					"Carnes a la parrilla, frijoles, chili", true);

			odb.store(p1);
			odb.store(p2);
			odb.store(p3);
			odb.store(p4);
			odb.store(p5);

			Especia e1 = new Especia("Azafran", "La Mancha, España", 15.2, 0, p1);

			e1.getUsos().add(p4);
			e1.getUsos().add(p3);

			Especia e2 = new Especia("Pimienta Negra", "Kerala, India", 850.5, 7, p5);
			e2.getUsos().add(p3);
			e2.getUsos().add(p2);

			Especia e3 = new Especia("Pimienta Negra", "La Mancha, España", 450.5, 6, p3);

			odb.store(e1);
			odb.store(e2);
			odb.store(e3);

			odb.commit();

			System.out.printf("Exito se ha añadido tipo de cocina %s y %s y %s y %s y %s ", p1.getTipoCocina(),
					p2.getTipoCocina(), p3.getTipoCocina(), p4.getTipoCocina(), p5.getTipoCocina());
			System.out.printf("Tambien se añadido especies : %s y %s", e1.getNombre(), e2.getNombre());
			return true;
		} catch (ODBRuntimeException e) {
			System.out.println("Error al crear y meter primeros datos a la base de datos ");
			System.out.println("Detalle de error: " + e.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error al intentar cerrar la conexion. Detalle de error: " + e2.getMessage());
				}
			}
		}
		return false;
	}

	public boolean introducir(Especia e) {
		ODB odb = null;
		try {
			odb = openBD();

			odb.store(e);
			odb.commit();

			System.out.println("Exito al Introducir Especie : " + e.getNombre() + " a la base da datos!");
			return true;

		} catch (ODBRuntimeException e1) {
			System.out.println("Error al Intentar Introducir nueva especie: " + e.getNombre());

			System.out.println("Detalle de error: " + e1.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error al intentar cerrar la conexion. Detalle de error: " + e2.getMessage());
				}
			}
		}
		return false;

	}

	public boolean modificar(String tipo, String str) {
		ODB odb = null;
		try {
			odb = openBD();

			ICriterion c1 = Where.equal("tipoCocina", tipo);
			IQuery q = new CriteriaQuery(PerfilSabor.class, c1);

			Objects<PerfilSabor> obj = odb.getObjects(q);

			if (obj.isEmpty()) {
				System.out.println("Consulta : 0 Resultados");
				System.out.println("No se encontro : " + tipo + " en la base de datos");
				return false;

			} else {
				PerfilSabor aux = obj.getFirst();
				aux.setMaridajeIdeal(str);

				odb.store(aux);
				odb.commit();

				System.out.println("Exito al modificar " + tipo + " ahora su nuevo sabor es: " + str);
				return true;
			}

		} catch (ODBRuntimeException e) {
			System.out.println("Error al intentar modificar el " + tipo);
			System.out.println("Detalle de error: " + e.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error al intentar cerrar la conexion. Detalle de error: " + e2.getMessage());
				}
			}
		}
		return false;
	}

	public boolean eliminar(String tipo) {
		ODB odb = null;
		try {
			odb = openBD();
			ICriterion c1 = Where.equal("tipoCocina", tipo);
			IQuery q = new CriteriaQuery(PerfilSabor.class, c1);

			Objects<PerfilSabor> obj = odb.getObjects(q);
			if (obj.isEmpty()) {
				System.out.println("Consulta 0 Resultados");
				System.out.println("no se encontro Perfil de Sabores: " + tipo + " en la base de datos");
				return false;
			} else {
				odb.delete(obj.getFirst());
				System.out.println("Exito se ha borrado el Perfil de Sabor : " + tipo);
			}

		} catch (ODBRuntimeException e) {
			System.out.println("Error al intentar borrar Perfil de Sabores: " + tipo);
			System.out.println("Detalle de error: " + e.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error alintentar cerrar la conexion.Detalle de error: " + e2.getMessage());
				}
			}
		}
		return false;
	}

	public void listar(String origen) {
		ODB odb = null;
		try {
			odb = openBD();

			ICriterion c1 = Where.like("origenGeografico", "%"+origen );
			IQuery q = new CriteriaQuery(Especia.class, c1);

			Objects<Especia> obj = odb.getObjects(q);

			if (obj.isEmpty()) {
				System.out.println("Consulta 0 Resultados");
				System.out.println("No se encontro especies de origen: " + origen + " en la base de datos");
			} else {
				System.out.println("Origen Geografico: " + origen);
				System.out.println("-----Especias encontradas:");
				while (obj.hasNext()) {
					Especia esp = obj.next();
					System.out.println(esp.toString());

				}
			}

		} catch (ODBRuntimeException e) {
			System.out.println("Error al ");
			System.out.println("Detalle de error: " + e.getMessage());
		} finally {
			if (odb != null) {
				try {
					odb.close();
				} catch (ODBRuntimeException e2) {
					System.err.println("Error al intentar cerrar la conexion. Detalle de error: " + e2.getMessage());
				}
			}
		}
	}
}

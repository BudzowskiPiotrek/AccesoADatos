package ejercicio04y05;

import java.time.LocalDate;
import java.util.Date;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * Clase principal que resuelve los Ejercicios 4 y 5 (DB4O) en un único archivo.
 * El Ejercicio 4 consiste en: 1. Crear la Base de Datos (al abrirla). 2. Llenar
 * las tablas PRODUCTOS y CLIENTES. 3. Visualizar los datos insertados y el
 * número de filas.
 * 
 * El Ejercicio 5 consiste en: 1. Implementar la inserción de una venta. 2.
 * Realizar las comprobaciones de unicidad de ID de Venta, existencia de Cliente
 * y Producto, y cantidad > 0. Nota: Se asume la existencia de las clases modelo
 * Clientes, Productos y Ventas con sus constructores y getters.
 * 
 * @author CRISTOBAL PRIMERO DE SU NOMBRE
 * @version 1.0
 */
public class Ejercicio04 {
	/** Ruta del archivo de la base de datos DB4O. */
	final static String BDPer = "C:/Users/Wilku/Documents/GitHub/AccesoADatos/DB40_Ejercicios/Ventas.yap";

	public static void main(String[] args) {

		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

		Clientes c1 = new Clientes(1, "Diego", "Direccion falsa 123", "Malaga", "635456123", "AGE 4561235");
		db.store(c1);
		Productos p1 = new Productos(101, "Zanahoria (Kg)", 150, 20, 1.25);
		db.store(p1);
		Ventas v1 = new Ventas(0, null, c1, p1, 50);

		// db.store(v1); // EJERCICIO 4
		insertarVentaSegura(db, v1.getIdVenta(), c1.getId(), p1.getId(), 20); // EJERCICIO 5

		ObjectSet<Clientes> clientes = db.query(Clientes.class);
		System.out.println("CLIENTES: " + clientes.size() + " registros:");
		for (Clientes c : clientes) {
			System.out.printf("Cliente :%s | Direcciones:%s | Poblacion:%s  \n", c.getNombre(), c.getDireccion(),
					c.getPoblacion());

		}

		ObjectSet<Productos> productos = db.query(Productos.class);
		System.out.println("PRODUCTOS: " + productos.size() + " registros:");
		for (Productos p : productos) {
			System.out.println(p.getDescripcion());
		}

		ObjectSet<Ventas> ventas = db.query(Ventas.class);
		System.out.println("VENTAS: " + ventas.size() + " registros:");
		for (Ventas v : ventas) {
			System.out.println(
					"Producto: " + v.getProducto().getDescripcion() + " - Cliente: " + v.getCliente().getNombre());
		}

		db.close();
	}

	/**
	 * Intenta insertar un registro de Venta, realizando las siguientes
	 * comprobaciones: La cantidad debe ser mayor que 0. El identificador de venta
	 * (idVenta) no debe existir previamente. El identificador de cliente
	 * (idCliente) debe existir en la BD. El identificador de producto (idProducto)
	 * debe existir en la BD.
	 * 
	 * @param db         Objeto ObjectContainer que representa la conexión a la BD
	 *                   DB4O.
	 * @param idVenta    ID de la venta a insertar (clave primaria).
	 * @param idCliente  ID del cliente de la venta (clave ajena).
	 * @param idProducto ID del producto de la venta (clave ajena).
	 * @param cantidad   Cantidad de unidades vendidas.
	 */
	public static void insertarVentaSegura(ObjectContainer db, int idVenta, int idCliente, int idProducto,
			int cantidad) {

		if (cantidad <= 0) {
			System.out.println("Error: La cantidad debe ser mayor que 0.");
			return;
		}

		// queryByExample ( BUSCA CONCIDENCIAS CON ALGUN VALOR QUE NO ESTE POR DEFECTO
		// EJEMPLO NULL, 0..... )
		Ventas v = new Ventas(idVenta, null, null, null, 0);
		ObjectSet<Ventas> venta = db.queryByExample(v);
		if (venta.size() > 0) {
			System.out.println("Error: El identificador de venta ya existe.");
			return;
		}

		Clientes c = new Clientes(idCliente, null, null, null, null, null);
		ObjectSet<Clientes> clientes = db.queryByExample(c);
		if (clientes.size() == 0) {
			System.out.println("Error: El identificador de cliente no existe.");
			return;
		}

		Clientes clienteEncontrado = clientes.next();

		Productos p = new Productos(idProducto, null, 0, 0, 0);
		ObjectSet<Productos> productos = db.queryByExample(p);
		if (productos.size() == 0) {
			System.out.println("Error: El identificador de producto no existe.");
			return;
		}

		Productos productoEncontrado = productos.next();

		LocalDate fechaActual = LocalDate.now();

		Ventas nuevaVenta = new Ventas(idVenta, fechaActual, clienteEncontrado, productoEncontrado, cantidad);
		db.store(nuevaVenta);
		db.commit();

		System.out.println("Venta ID insertada correctamente.");
	}

}

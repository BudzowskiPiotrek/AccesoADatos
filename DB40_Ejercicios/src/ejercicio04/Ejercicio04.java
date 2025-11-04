package ejercicio04;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Ejercicio04 {
	final static String BDPer = "C:/Users/Wilku/Documents/GitHub/AccesoADatos/DB40_Ejercicios/Ventas.yap";

	public static void main(String[] args) {

		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

		Clientes c1 = new Clientes(1, "Diego", "Direccion falsa 123", "Malaga", "635456123", "AGE 4561235");
		Productos p1 = new Productos(0, "Zanahoria", 0, 0, 0);
		Ventas v1 = new Ventas(0, null, c1, p1, 50);

		db.store(v1);

		ObjectSet<Clientes> clientes = db.query(Clientes.class);
		System.out.println("CLIENTES: " + clientes.size() + " registros:");
		for (Clientes c : clientes) {
			System.out.printf("Cliente :%s | Direcciones:%s | Poblacion:%s  \n", c.getNombre(), c.getDireccion(), c.getPoblacion());
	
		}

		ObjectSet<Productos> productos = db.query(Productos.class);
		System.out.println("PRODUCTOS: " + productos.size() + " registros:");
		for (Productos p : productos) {
			System.out.println(p.getDescripcion());
		}

		ObjectSet<Ventas> ventas = db.query(Ventas.class);
		System.out.println("VENTAS: " + ventas.size() + " registros:");
		for (Ventas v : ventas) {
			System.out.println("Producto: " + v.getProducto().getDescripcion() + " - Cliente: " + v.getCliente().getNombre());
		}

		db.close();
	}

}

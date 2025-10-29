package ejercicio04y05;

public class App {

	public static void main(String[] args) {
		DBConeccion bd = new DBConeccion();
		bd.conectar();
		Cliente c1 = new Cliente(1, "nombre", "direccion", "poblacion", "telefon", "nif");
		Productos p1 = new Productos(0, "nombre", 0, 0, 0);
		int numero = bd.agregarCliente(c1);

		numero += bd.agregarProducto(p1);
		System.out.println("filas afectadas: " + numero);
		Ventas v1 = new Ventas(0, c1.getId(), p1.getId(), 20);
		
		if(bd.agregarVenta(v1)==1) {
			System.out.println("Exito");
		}
		
		bd.desconectar();
	}

}

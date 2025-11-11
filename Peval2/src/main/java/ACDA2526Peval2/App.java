package ACDA2526Peval2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
	private static final String RUTA = "jdbc:mysql://localhost/astronomia";
	private static final String USUARIO = "root";
	private static final String CONTRA = "";
	private static boolean ejecutado = true;

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection(RUTA, USUARIO, CONTRA)) {
			Consultas consults = new Consultas(con);
			menu(consults);
			System.out.println("Adios Enrique");
		} catch (SQLException e) {
			System.out.println("Error al intentar coenctar a base de datos");
		}

	}

	private static void menu(Consultas consults) {
		Scanner sn = new Scanner(System.in);
		while (ejecutado) {
			textoMenu();
			int opcion = sn.nextInt();
			switchMenu(opcion, consults);
		}
		sn.close();
	}

	private static void switchMenu(int opcion, Consultas consults) {
		switch (opcion) {
		case 1:
			insertarEstrella(consults);
			break;
		case 2:
			borrarEstrella(consults);
			break;
		case 3:
			modificarPlaneta(consults);
			break;
		case 4:
			borrarDescubridor(consults);
			break;
		case 5:
			consults.imprimir();
			break;
		case 6:
			ejecutado=false;
			break;

		default:
			break;
		}
	}

	private static void borrarDescubridor(Consultas consults) {
		int numero = consults.borrarDescubridor(1006); // si ponnes 1001 trycatch te informa que no peudes que se usa en otra tabla
		if(numero==1) {
			System.out.println("Exito!!el descubridor borrado");
		}else {
			System.err.println("No se pudo encontrarlo/borarlo");
		}
	}

	private static void modificarPlaneta(Consultas consults) {
		int numero = consults.actualizarPlaneta("Saturno2", 20.1);
		if(numero==1) {
			System.out.println("Exito!!La masafue modificada");
		}else {
			System.err.println("No se pudo encontrarlo/modificarlo");
		}
	}

	private static void borrarEstrella(Consultas consults) {
		int numero = consults.eliminarEstrella("Prueba");
		if(numero==1) {
			System.out.println("Exito!!");
		}else {
			System.err.println("No se pudo encontrarlo/borrarlo");
		}
	}

	private static void insertarEstrella(Consultas consults) {
		int numero = consults.ingresarEstrella(new Estrellas("Prueba", 1.1, 1.1, 1.1, 12.2, "AE Andrómeda", "O","IC 10", 1001, LocalDate.now()));
		if(numero==1) {
			System.out.println("Exito!!Nueva Estrella ingresada");
		}
	}

	private static void textoMenu() {
		System.out.println("---- Elige ----");
		System.out.println("1. Insertar Una estrella");
		System.out.println("2. Eliminar una estrella");
		System.out.println("3. Actualizar los datos de un planeta");
		System.out.println("4. Borrar un descubridor");
		System.out.println("5. Listar todos los cuerpos celestes existentes");
		System.out.println("6. Salir");
	}

}

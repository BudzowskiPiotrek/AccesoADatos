package peval3acda2526;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static Scanner sn = new Scanner(System.in);

	public static void main(String[] args) {
		MetodosNeodatis metodos = new MetodosNeodatis();
		int opcion = -1;

		while (opcion != 0) {
			menuTexto();
			opcion = comprobacionNumero("Elige una opcion");
			
			switch (opcion) {
			case 1:
				metodos.creacion();
				break;
			case 2:
				metodos.introducir(new Especia("Chili", "Cambolla", 20.2, 3,
						new PerfilSabor("Chile", "Picante", "Algo Algo", false)));
				// si tendria mas tiempo te haria una cosulta para sacarlo de la base de datos
				// ya existente, en este examen me dedique mas a try catch y avisos =)
				break;
			case 3:
				metodos.modificar("Europea", "Dulce, especiado y mucha maiz");
				break;
			case 4:
				metodos.eliminar("Mexicana");
				break;
			case 5:
				metodos.listar("La Mancha, Espa�a");
				break;
			case 0:
				System.out.println("Adios Enrique");
				break;
			default:
				System.out.println("Intenta otra vez");
				break;
			}
		}
		sn.close();
	}

	private static void menuTexto() {
		System.out.println("\n1. Creaci�n ");
		System.out.println("2. Introducci�n");
		System.out.println("3. Modificaci�n");
		System.out.println("4. Eliminaci�n");
		System.out.println("5. Listar");
		System.out.println("0. Salir");

	}

	public static int comprobacionNumero(String str) {
		while (true) {
			System.out.println(str);
			try {
				int num = sn.nextInt();
				sn.nextLine();
				return num;
			} catch (InputMismatchException e) {
				System.err.println("Puedes ingresar solo los numeros enteros, intente otra vez");
				sn.nextLine();
			}
		}
	}
}

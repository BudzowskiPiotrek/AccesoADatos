package ejercicio04;

import java.io.File;
import java.util.Scanner;

public class App {
	// Realiza un programa Java que muestre los ficheros de un directorio.
	// El nombre del directorio se pasará al programa por teclado.
	// Si el directorio no existe se debe mostrar un mensaje indicándolo.
	public static void main(String[] args) {
		Scanner st = new Scanner(System.in);
		String rutaUsuario = System.getProperty("user.home");
		String rutaPantalla = rutaUsuario + "\\Desktop\\";

		System.out.println("Introduce la ruta de un directorio:");
		String rutaDirectorio = rutaPantalla + st.nextLine();

		File directorio = new File(rutaDirectorio);

		if (directorio.exists() && directorio.isDirectory()) {
			System.out.println(directorio.getAbsolutePath() + "':\n");
			File[] listaFicheros = directorio.listFiles();

			if (listaFicheros != null) {
				for (File fichero : listaFicheros) {
					System.out.println(fichero.getName());
				}
			} else {
				System.err.println("No hay ficheros dentro");
			}
		} else {
			System.err.println("No existe o no es un directorio.");
		}
	}
}

package ejercicio03a;

import java.io.File;
import java.util.Scanner;

public class App {
	// Realiza un programa Java que reciba un nombre de directorio y posteriormente
	// borre el directorio junto a todo su contenido. Realizar el ejercicio con el
	// uso de recursividad y sin el uso de ésta.
	public static void main(String[] args) {
		Scanner st = new Scanner(System.in);
		String rutaUsuario = System.getProperty("user.home");
		String rutaPantalla = rutaUsuario + "\\Desktop\\";

		System.out.println("Introduce la ruta de un directorio:");
		String rutaDirectorio = rutaPantalla + st.nextLine();

		File directorio = new File(rutaDirectorio);

		if (directorio.exists() && directorio.isDirectory()) {
			if (borrarDirectorio(directorio)) {
				System.out.println("El directorio y todo borado");
			} else {
				System.err.println("No se pudo borrar.");
			}
		} else {
			System.err.println("No existe o no es directorio");
		}
	}

	private static boolean borrarDirectorio(File directorio) {
		File[] listaContenido = directorio.listFiles();
		if (listaContenido != null) {
			for (File elemento : listaContenido) {
				if (elemento.isDirectory()) {
					borrarDirectorio(elemento);
				} else {
					System.out.println("Borrando: " + elemento.getAbsolutePath());
					elemento.delete();
				}
			}
		}
		System.out.println("Borrando: " + directorio.getAbsolutePath());
		return directorio.delete();
	}
}

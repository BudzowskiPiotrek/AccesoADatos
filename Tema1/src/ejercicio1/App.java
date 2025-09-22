package ejercicio1;

import java.io.File;
import java.util.Scanner;

public class App {
	// Realiza un programa Java que utilice el método listFiles() para mostrar la
	// lista de ficheros en un directorio cualquiera, o en el directorio actual.
	public static void main(String[] args) {
		String rutaUsuario = System.getProperty("user.home");
		String rutaEscritorio = rutaUsuario + "\\Desktop\\Prueba";
		File directorio = new File(rutaEscritorio);

		if (directorio.exists() && directorio.isDirectory()) {
			System.out.println(directorio.getAbsolutePath() + "':");
			File[] listaFicheros = directorio.listFiles();
			if (listaFicheros != null) {

				for (File fichero : listaFicheros) {
					System.out.println(fichero.getName());
				}
			} else {
				System.err.println("No se pudo obtener la lista de ficheros");
			}
		} else {
			System.err.println("La ruta no existe o no es un directorio");
		}
	}
}

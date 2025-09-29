package ejercicio02a;

import java.io.File;

public class App {
	// Modificar el programa anterior para que liste todo el árbol de directorios,
	// en el caso que tenga más directorios en su interior. Realizar el ejercicio
	// con el uso de recursividad y sin el uso de ésta.
	public static void main(String[] args) {
		String rutaUsuario = System.getProperty("user.home");
		String rutaDirectorio = rutaUsuario + "\\Desktop\\prueba";
		File directorio = new File(rutaDirectorio);

		System.out.println(directorio.getAbsolutePath() + "\n");
		listarContenido(directorio, 0);
	}

	public static void listarContenido(File directorio, int nivel) {
		if (directorio.exists() && directorio.isDirectory()) {
			File[] lista = directorio.listFiles();

			if (lista != null) {
				for (File f : lista) {
					System.out.print("    ".repeat(nivel));

					if (f.isDirectory()) {
						System.out.println("--\\" + f.getName());
						listarContenido(f, nivel + 1);
					} else {
						System.out.println("-- " + f.getName());
					}
				}
			}
		}else {
			System.err.println("No existe esta carpeta");
		}
	}
}

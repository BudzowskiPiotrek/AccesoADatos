package ejercicio2b;

import java.io.File;
import java.util.Stack;

public class App {
	// Modificar el programa anterior para que liste todo el árbol de directorios,
	// en el caso que tenga más directorios en su interior. Realizar el ejercicio
	// con el uso de recursividad y sin el uso de ésta.
	public static void main(String[] args) {
		String rutaUsuario = System.getProperty("user.home");
		String rutaDirectorio = rutaUsuario + "\\Desktop\\prueba";
		File directorio = new File(rutaDirectorio);

		System.out.println(directorio.getAbsolutePath() + "\n");
		listarContenido(directorio);
	}

	public static void listarContenido(File directorio) {
		if (!directorio.exists() || !directorio.isDirectory()) {
			System.err.println("La ruta no existe o no es un directorio.");
			return;
		}
		
		Stack<File> pilaDirectorios = new Stack<>();
		pilaDirectorios.push(directorio);
		Stack<Integer> pilaNiveles = new Stack<>();
		pilaNiveles.push(0);
		
		while (!pilaDirectorios.isEmpty()) {
            File directorioAccion = pilaDirectorios.pop();
            int nivelAccion = pilaNiveles.pop();

            File[] listaElementos = directorioAccion.listFiles();

            if (listaElementos != null) {
                for (int i = listaElementos.length - 1; i >= 0; i--) {
                    File elemento = listaElementos[i];
                    String prefijo = "    ".repeat(nivelAccion);

                    if (elemento.isDirectory()) {
                        System.out.println(prefijo + "--\\ " + elemento.getName());
                        pilaDirectorios.push(elemento);
                        pilaNiveles.push(nivelAccion + 1);
                    } else {
                        System.out.println(prefijo + "-- " + elemento.getName());
                    }
                }
            }
		}
	}
}

package ejercicio3b;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		 Scanner st = new Scanner(System.in);
	        String rutaUsuario = System.getProperty("user.home");
	        String rutaPantalla = rutaUsuario +"\\Desktop\\";

	        System.out.println("Introduce el nombre del directorio a borrar:");
	        String rutaDirectorio = rutaPantalla + st.nextLine();
	        
	        File directorio = new File(rutaDirectorio);

	        if (directorio.exists() && directorio.isDirectory()) {
	            if (borrarDirectorio(directorio)) {
	                System.out.println("El directorio y todo su contenido han sido borrados con éxito.");
	            } else {
	                System.err.println("No se pudo borrar");
	            }
	        } else {
	            System.err.println("No existe o no es un directorio.");
	        }
	        
	}

	private static boolean borrarDirectorio(File directorio) {
		ArrayList<File> lista = new ArrayList<>();
        lista.add(directorio);
        for (int i = 0; i < lista.size(); i++) {
            File elementoTurno = lista.get(i);
            if (elementoTurno.isDirectory()) {
                File[] Contenido = elementoTurno.listFiles();
                if (Contenido != null) {
                    for (File f : Contenido) {
                        lista.add(f);
                    }
                }
            }
        }
        Collections.reverse(lista);
        for (File f : lista) {
            System.out.println("Borrando: " + f.getAbsolutePath());
            if (!f.delete()) {
                return false;
            }
        }
		return true;
	}

}

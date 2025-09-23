package practica05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		String rutaUsuario = System.getProperty("user.home");
		String rutaPantalla = rutaUsuario + "\\Desktop\\";
		File fichero = new File(rutaPantalla + "fichero.txt");
		FileWriter fic = new FileWriter(fichero, true);
		
		String cadena = "Prueba para FileWriter";
	
		char[] cad = cadena.toCharArray();
		for (int i = 0; i < cad.length; i++)
			fic.write(cad[i]); // se va escribe por caracter
		fic.append('*'); // añadir al final un *
		fic.close();
	}

}

package practica04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		String rutaUsuario = System.getProperty("user.home");
		String rutaPantalla = rutaUsuario + "\\Desktop\\";
		File fichero = new File(rutaPantalla + "fichero.txt");

		FileReader fic = null;
		try {
			fic = new FileReader(fichero);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i;
		try {
			while ((i = fic.read()) != -1)
				System.out.print((char) i); // Se cambia println por print
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fic.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

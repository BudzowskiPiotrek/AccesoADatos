package practica06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
	public static void main(String[] args) {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("Ej06_LeerFichTextoBuf.java"));
			String linea;
			while ((linea = fichero.readLine()) != null)
				System.out.println(linea);
			fichero.close();
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S");
		}
	}
}

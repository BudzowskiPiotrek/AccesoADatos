package ejercicio08;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
	// Crea un fichero de texto con algún editor de textos y después realiza un
	// programa Java que visualice su contenido. Cambia el programa java para que el
	// nombre del fichero se acepte desde teclado.
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce el nombre del fichero de texto a visualizar (ej. 'prueba.txt'): ");
		String nombreFichero = scanner.nextLine();
		scanner.close();

		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			System.out.println("--- Contenido  ---");
			String linea;
			int contadorLineas = 0;

			while ((linea = br.readLine()) != null) {
				contadorLineas++;
				System.out.println(linea);
			}

			if (contadorLineas == 0) {
				System.out.println("El fichero existe pero está vacío.");
			}

			System.out.println("------------------");
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: El fichero no se ha encontrado");
		} catch (IOException e) {
		}
	}
}

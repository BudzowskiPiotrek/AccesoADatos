package ejercicio4b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		String rutaFicheroEntrada = System.getProperty("user.home") + "\\Desktop\\fichero.txt";
		String rutaFicheroSalida = System.getProperty("user.home") + "\\Desktop\\fichero_modificado.txt";
				
		try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroEntrada));
				BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFicheroSalida))) {

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split(" ");
				if (campos[0].equalsIgnoreCase("Piotrek")) {
					campos[0] = "Carlos";
					StringBuilder nuevaLinea = new StringBuilder();
					for (int i = 0; i < campos.length; i++) {
						nuevaLinea.append(campos[i]);
						if (i < campos.length - 1) {
							nuevaLinea.append(" ");
						}
					}
					bw.write(nuevaLinea.toString());
				} else {
					bw.write(linea);
				}
				bw.newLine(); 
			}
		} catch (IOException e) {
			System.err.println("Ocurrió un error al leer o escribir el fichero:");
			e.printStackTrace();
		}
		
		File f1 = new File(rutaFicheroEntrada);
		File f2 = new File(rutaFicheroSalida);
		
		f1.delete();
		f2.renameTo(f1);
		
		
	}
}

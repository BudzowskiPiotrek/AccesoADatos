package ejercicio4b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ModificarFichero {

	public static void main(String[] args) {
		// CREAO LAS RUTAS DEL FICHERO Y RUTA DEL FICHERO AUXILIAR
		// EL FICHERO ESTA COMPUESTO POR DNI-APELLIDO-NOMBRE
		String rutaFicheroEntrada = System.getProperty("user.home") + "\\Desktop\\fichero.txt";
		String rutaFicheroSalida = System.getProperty("user.home") + "\\Desktop\\fichero_auxiliar.txt";
		Scanner st = new Scanner(System.in);
		// LE PREGUNTAMOS POR EL APELLIDO CUAL SE NODIFICARA
		System.out.println("Dime el dni para poder modificar su nombre y apellido:");
		String dni = st.nextLine();
		System.out.println("Dime su nuevo Apellido correspondiente:");
		String apellido = st.nextLine();
		System.out.println("Dime su nuevo Nombre correspondiente:");
		String nombre = st.nextLine();

		// CREAMOS BR Y BW DENTRO DE TRY USANDO SU RECURSO
		try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroEntrada));
				BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFicheroSalida))) {
			String linea;
			// RECOREMOS LINEA POR LINEA CONVIERTIENDOLA EN UNA ARRAY BUSCANDO CONCIDENCIA
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split(" ");
				if (campos.length >= 3 && campos[0].equalsIgnoreCase(dni)) {
					campos[1] = apellido;
					campos[2] = nombre;
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
				bw.newLine(); // \n
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ULTIMA FASE DE EJERCICIO BORAMOS EL PRIMERO Y SEGUNDO RENOMBRAMOS
		File f1 = new File(rutaFicheroEntrada);
		File f2 = new File(rutaFicheroSalida);

		f1.delete();
		f2.renameTo(f1);
		st.close();
	}
}

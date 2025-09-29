package ejercicio04b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ModificarFichero {

	public static void main(String[] args) {
		// CREAO LAS RUTAS DEL FICHERO Y RUTA DEL FICHERO AUXILIAR
		// EL FICHERO ESTA COMPUESTO POR DNI-APELLIDO-NOMBRE
		String rutaFicheroEntrada = System.getProperty("user.home") + "\\Desktop\\fichero.txt";
		String rutaFicheroSalida = System.getProperty("user.home") + "\\Desktop\\fichero_auxiliar.txt";

		String dni = null, apellido = null, nombre = null;

		// BUCLE PARA SOLICITAR Y VALIDAR LA ENTRADA DEL USUARIO
		// AL ESTAR SCANER EN TRY-RESOURSES LOS NEXTLINE NO LANZAN "InputMismatchException"
		// Y TAMBIEN CIERRA SCANNER
		try (Scanner st = new Scanner(System.in)) {
			do {
				System.out.println("Dime el DNI para modificar su nombre y apellido:");
				dni = st.nextLine().trim();
				// VALIDAR QUE EL DNI NO ESTÉ VACÍO
				if (dni.isEmpty()) {
					System.err.println("Error: EL DNI VACIO. Intentalo de nuevo.");
				}
			} while (dni.isEmpty());

			do {
				System.out.println("Dime el nuevo Apellido:");
				apellido = st.nextLine().trim();
				// VALIDAR QUE EL APELLIDO NO ESTÉ VACÍO
				if (apellido.isEmpty()) {
					System.err.println("Error: EL APELLIDO VACIO. Intentalo de nuevo.");
				}
			} while (apellido.isEmpty());

			do {
				System.out.println("Dime el nuevo Nombre:");
				nombre = st.nextLine().trim();
				// VALIDAR QUE EL NOMBRE NO ESTÉ VACÍO
				if (nombre.isEmpty()) {
					System.err.println("Error: EL NOMBRE VACIO. Intentalo de nuevo.");
				}
			} while (nombre.isEmpty());

			File f1 = new File(rutaFicheroEntrada);
			File f2 = new File(rutaFicheroSalida);

			// CONTROL DE ERROR PARA VERIFICAR SI EL ARCHIVO DE ENTRADA EXISTE
			if (!f1.exists()) {
				System.err.println("Error: El archivo de entrada no se encuentra");
				return;
			}

			boolean encontrado = false; // FLAG PARA SABER SI SE HA ENCONTRADO EL DNI

			// USAMOS TRY-RESOURCES PARA ASEGURAR QUE LOS RECURSOS SE CIERREN
			// AUTOMATICAMENTE
			try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroEntrada));
					BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFicheroSalida))) {
				String linea;
				// RECOREMOS LINEA POR LINEA CONVIERTIENDOLA EN UNA ARRAY BUSCANDO CONCIDENCIA
				while ((linea = br.readLine()) != null) {
					// USAMOS SPLIT CON UN LIMITE PARA EVITAR ERRORES SI HAY ESPACIOS ADICIONALES
					String[] campos = linea.split(" ", 3);
					// VERIFICAMOS SI LA LÍNEA TIENE EL FORMATO ESPERADO 3 CAMPOS
					if (campos.length >= 3 && campos[0].equalsIgnoreCase(dni)) {
						// SI ENCONTRAMOS EL DNI, MODIFICAMOS LA LÍNEA
						bw.write(dni + " " + apellido + " " + nombre);
						encontrado = true;
					} else {
						// SI NO ES LA LÍNEA QUE BUSCAMOS, LA ESCRIBIMOS TAL CUAL
						bw.write(linea);
					}
					bw.newLine(); // AÑADIMOS UNA NUEVA LÍNEA AL FINAL
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!encontrado) {
				System.out.println("El DNI " + dni + " no se encontró en el fichero.");
				if (!f2.delete()) { // SI NO SE ENCUENTRA NO ES NECESARIO SEGUNDO FICHERO
					System.err.println("No se pudo eliminar el archivo auxilial.");
				}
			} else {
				System.out.println("El DNI " + dni + " fue encontrado y modificado.");
				if (f1.delete()) { // SI SE ENCONTRO PUES SE HACE CAMBIAZO
					if (!f2.renameTo(f1)) {
						System.err.println(
								"No se pudo renombrar el archivo auxiliar pero el original fue borrado.");
					}
				} else {
					System.err.println("No se pudo borrar el archivo original.");
				}
			}
		} catch (Exception e) {
			System.err.println("Error: inesperado durante la ejecuciOn");
			return;
		}
	}
}

package ejercicio09;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
	// Crea un fichero Java que reciba un identificador de un empleado desde teclado
	// y visualice sus datos. Si el empleado no existe debe visualizar el mensaje.
	private static final String FICHERO = "Empleados.dat";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int idBuscado = -1;
		boolean encontrado = false;

		System.out.print("Introduce el ID del empleado a buscar: ");
		idBuscado = scanner.nextInt();
		scanner.close();

		try (DataInputStream dis = new DataInputStream(new FileInputStream(FICHERO))) {

			while (!encontrado) {
				try {
					int id = dis.readInt();
					String nombre = dis.readUTF();
					double salario = dis.readDouble();

					if (id == idBuscado) {
						encontrado = true;
						System.out.printf("ID: %d | Nombre: %s | Salario: %f € ", id, nombre, salario);
					}
				} catch (EOFException e) {
					encontrado = true;
				}
			}

			if (!encontrado) {
				System.out.println("El empleado NO EXISTE");
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: El fichero no existe. Debes crearlo primero.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
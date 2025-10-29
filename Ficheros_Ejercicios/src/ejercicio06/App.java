package ejercicio06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class App {
	// Realiza un programa Java que te permita modificar los datos de departamento.
	// El programa lee el número de departamento a modificar, el nuevo nombre de departamento
	// y la nueva localidad. Si el departamento no existe visualiza un mensaje indicándolo.
	// Visualiza también los datos antiguos del departamento y los nuevos datos. 
	public static void main(String[] args) {
		final String ORIGINAL = "Departamentos.dat";
		final String TEMPORAL = "TempDepartamentos.dat";

		Scanner sn = new Scanner(System.in);
		int numDeptoModificar = -1;
		String nuevoNombre = "";
		String nuevaLocalidad = "";
		boolean encontrado = false;
		Departamento deptoAntiguo = null;

		System.out.print("Introduce el NÚMERO del departamento a modificar: ");
		numDeptoModificar = sn.nextInt();
		sn.nextLine();

		System.out.print("Introduce el NUEVO NOMBRE del departamento: ");
		nuevoNombre = sn.nextLine();

		System.out.print("Introduce la NUEVA LOCALIDAD del departamento: ");
		nuevaLocalidad = sn.nextLine();
		sn.close();

		try (DataInputStream dis = new DataInputStream(new FileInputStream(ORIGINAL));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(TEMPORAL))) {
			while (true) {
				try {
					int numero = dis.readInt();
					String nombre = dis.readUTF();
					String localidad = dis.readUTF();
					Departamento depto = new Departamento(numero, nombre, localidad);

					if (depto.getNumero() == numDeptoModificar) {
						encontrado = true;
						deptoAntiguo = new Departamento(numero, nombre, localidad);

						depto.setNombre(nuevoNombre);
						depto.setLocalidad(nuevaLocalidad);
					}

					dos.writeInt(depto.getNumero());
					dos.writeUTF(depto.getNombre());
					dos.writeUTF(depto.getLocalidad());

				} catch (EOFException e) {
					break;
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("¡ERROR! El fichero no existe.");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		if (encontrado) {
			System.out.println("\nDepartamento ENCONTRADO y MODIFICADO");

			System.out.println("DATOS ANTIGUOS:");
			System.out.printf("--/ Número: %d, Nombre: %s, Localidad: %s\n", deptoAntiguo.getNumero(),
					deptoAntiguo.getNombre(), deptoAntiguo.getLocalidad());

			System.out.println("DATOS NUEVOS:");
			System.out.printf("--/ Número: %d, Nombre: %s, Localidad: %s\n", numDeptoModificar, nuevoNombre,
					nuevaLocalidad);

			File original = new File(ORIGINAL);
			if (original.delete()) {
				File temporal = new File(TEMPORAL);
				if (temporal.renameTo(original)) {
					System.out.println("\nModificación completada y fichero actualizado.");
				} else {
					System.err.println("\nERROR al renombrar el fichero temporal.");
				}
			} else {
				System.err.println("\nERROR al eliminar el fichero original.");
			}

		} else {
			new File(TEMPORAL).delete();
			System.out.println("\nEl departamento NO EXISTE en el fichero.");
		}
	}
}
package ejercicio07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import ejercicio06.Departamento;

public class App {
	// Realiza un programa java que te permita eliminar un departamento. El programa
	// lee el número de departamento a eliminar. Si el departamento no existe
	// visualiza un mensaje indicándolo. Visualiza también el número total de
	// departamentos que existen.
	public static void main(String[] args) {
		final String ORIGINAL = "Departamentos.dat";
		final String TEMPORAL = "TempDepartamentos.dat";

		Scanner sn = new Scanner(System.in);
		int numDepartamento = -1;
		int numTotal = -0;
		boolean encontrado = false;
		Departamento deptoAntiguo = null;

		System.out.print("Introduce el NÚMERO del departamento a BORRAR: ");
		numDepartamento = sn.nextInt();
		sn.nextLine();
		try (DataInputStream dis = new DataInputStream(new FileInputStream(ORIGINAL));
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(TEMPORAL))) {
			while (true) {
				try {
					int numero = dis.readInt();
					String nombre = dis.readUTF();
					String localidad = dis.readUTF();
					Departamento depto = new Departamento(numero, nombre, localidad);

					if (depto.getNumero() == numDepartamento) {
						encontrado = true;
						deptoAntiguo = new Departamento(numero, nombre, localidad);
						continue;
					} else {
						numTotal += 1;
						dos.writeInt(depto.getNumero());
						dos.writeUTF(depto.getNombre());
						dos.writeUTF(depto.getLocalidad());
					}

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
			System.out.println("\nDepartamento ENCONTRADO y BORRADO");

			System.out.println("DATOS BORADOS:");
			System.out.printf("--/ Número: %d, Nombre: %s, Localidad: %s\n", deptoAntiguo.getNumero(),
					deptoAntiguo.getNombre(), deptoAntiguo.getLocalidad());

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
		System.out.println("\nNúmero total de departamentos: " + numTotal);
	}
}

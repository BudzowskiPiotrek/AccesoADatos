package ejercicio10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	// Crea un programa java que reciba desde teclado un identificador de empleado y
	// un importe. Se debe sumar al salario del empleado el importe tecleado. El
	// programa debe visualizar el apellido, el salario antiguo y el nuevo. Si el
	// identificador no existe se visualizará un mensaje indicándolo
	public static List<Empleado> empleados = new ArrayList<>();
	public final static String RUTA = "Datos.obj";

	public static void main(String[] args) {
		cargarDatos();
		Scanner sn = new Scanner(System.in);
		System.out.println("Dime identificador del empleado:");
		int idTrabajador = sn.nextInt();

		System.out.println("¿Cuánto sumar de importe?");
		int sumar = sn.nextInt();
		modificarSalario(idTrabajador, sumar);
		guardarDatos();
		sn.close();
	}

	private static void modificarSalario(int id, int importe) {
		boolean encontrado = false;
		for (Empleado e : empleados) {
			if (e.getId() == id) {
				double salarioAntiguo = e.getSalario();
				encontrado = true;
				e.setSalario(salarioAntiguo + importe);
				System.out.println("Sueldo actualizado");
			}
		}
		if (!encontrado) {
			System.out.println("ERROR: El identificador de empleado no existe.");
		}
	}

	public static void crearDatosDePrueba() {
		empleados.add(new Empleado(101, "Perez", 500.0));
		empleados.add(new Empleado(102, "Lopez", 800.0));
		empleados.add(new Empleado(103, "Gomez", 300.0));
		empleados.add(new Empleado(104, "Diaz", 1200.0));
		empleados.add(new Empleado(105, "Ruiz", 650.0));
	}

	private static void guardarDatos() {
		try (FileOutputStream fos = new FileOutputStream(RUTA); ObjectOutputStream aux = new ObjectOutputStream(fos)) {
			aux.writeObject(empleados);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cargarDatos() {
		File archivo = new File(RUTA);
		if (!archivo.exists()) {
			crearDatosDePrueba();
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA))) {
			empleados = (List<Empleado>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
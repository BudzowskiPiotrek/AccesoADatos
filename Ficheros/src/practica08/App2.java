package practica08;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class App2 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Persona persona;
		File fichero = new File("FichPersona.dat");
		FileInputStream filein = new FileInputStream(fichero);

		ObjectInputStream dataIS = new ObjectInputStream(filein);
		try {
			while (true) {
				persona = (Persona) dataIS.readObject();
				persona.saludar();
				System.out.println("Nombre: " + persona.getNombre() + ", edad:" + persona.getEdad());
			}
		} catch (EOFException eo) {
		}
	}
}

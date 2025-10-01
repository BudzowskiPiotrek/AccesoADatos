package practica08;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class App {

	public static void main(String[] args) throws IOException {
		Persona persona;
		File fichero = new File("FichPersona.dat");
		FileOutputStream fileout = new FileOutputStream(fichero);
		ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
		String nombres[] = { "Ana", "Luis Miguel", "Alicia", "Pedro", "Manuel", "Andrés", "Julio", "Antonio",
				"María Jesús" };
		int edades[] = { 14, 15, 13, 15, 16, 12, 16, 14, 13 };

		for (int i = 0; i < edades.length; i++) {
			persona = new Persona(nombres[i], edades[i]);

			dataOS.writeObject(persona);
		}

		dataOS.close();
	}

}

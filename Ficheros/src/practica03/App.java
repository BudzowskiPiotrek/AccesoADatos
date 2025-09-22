package practica03;

import java.io.File;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
		File d = new File("NUEVODIR"); // directorio a crear
		File f1 = new File(d, "FICHERO1.TXT");
		File f2 = new File(d, "FICHERO2.TXT");

		d.mkdir();
		try {
			if (f1.createNewFile())
				System.out.println("FICHERO1 creado correctamente");
			else
				System.out.println("No se ha podido crear FICHERO1");
			if (f2.createNewFile())
				System.out.println("FICHERO2 creado correctamente");
			else
				System.out.println("No se ha podido crear FICHERO2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f1.renameTo(new File(d, "FICHERO1NUEVO.TXT")); // renombrar FICHERO1
		try {
			File f3 = new File("NUEVODIR/FICHERO3.TXT");
			f3.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Crear FICHERO3 en NUE
	}

}

package ejercicio05;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
	// Realiza un programa java que cree un fichero binario para guardar datos de
	// departamentos, dale el nombre Departamentos.dat. Introduce varios
	// departamentos. Los datos por cada departamento son: Número de departamento:
	// entero, Nombre: String y Localidad: String.

	    public static void main(String[] args) {
	        String nombreFichero = "Departamentos.dat";

	        Departamento[] departamentos = {
	            new Departamento(10, "VENTAS", "TOREMOLINO"),
	            new Departamento(20, "INVESTIGACION", "MALAGA"),
	        };

	        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombreFichero))) {
	            for (Departamento depto : departamentos) {
	                dos.writeInt(depto.getNumero()); 
	                dos.writeUTF(depto.getNombre());
	                dos.writeUTF(depto.getLocalidad());
	                System.out.printf("Departamento: %d - %s (%s) escrito.\n", 
	                                  depto.getNumero(), depto.getNombre(), depto.getLocalidad());
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
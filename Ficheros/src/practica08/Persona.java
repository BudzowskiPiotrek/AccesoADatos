package practica08;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int edad;

	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public Persona() {
		this.nombre = null;
	}

	public void setNombre(String nom) {
		nombre = nom;
	}

	public void setEdad(int ed) {
		edad = ed;
	}

	public String getNombre() {
		return nombre;
	} // devuelve el nombre

	
	public int getEdad() {
		return edad;
	} // devuelve edad
	
	public void saludar() {
		System.out.println("Hola");
	}
}

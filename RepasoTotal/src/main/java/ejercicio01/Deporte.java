package ejercicio01;

import java.io.Serializable;

public class Deporte implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;

	public Deporte(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Galaxia {
	private String nombre;
	private String tipo;
	private List<Estrella> estrellas;

	public Galaxia(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.estrellas = new ArrayList<>();
	}

	public void addEstrella(Estrella e) {
		estrellas.add(e);
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public List<Estrella> getEstrellas() {
		return estrellas;
	}
}
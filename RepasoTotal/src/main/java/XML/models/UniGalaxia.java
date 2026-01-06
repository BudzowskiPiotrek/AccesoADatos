package XML.models;

import java.util.ArrayList;
import java.util.List;

public class UniGalaxia {
	private String nombre;
	private String tipo;
	private List<UniEstrella> estrellas;

	public UniGalaxia(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.estrellas = new ArrayList<>();
	}

	public void addEstrella(UniEstrella e) {
		estrellas.add(e);
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public List<UniEstrella> getEstrellas() {
		return estrellas;
	}
}
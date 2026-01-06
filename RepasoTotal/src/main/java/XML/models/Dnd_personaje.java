package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Dnd_personaje {
	private String nombre;
	private List<Dnd_objeto> lista;

	public Dnd_personaje(String nombre) {
		this.lista = new ArrayList<>();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Dnd_objeto> getLista() {
		return lista;
	}

	public void setLista(List<Dnd_objeto> lista) {
		this.lista = lista;
	}

}

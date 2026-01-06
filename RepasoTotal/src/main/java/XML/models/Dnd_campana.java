package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Dnd_campana {
	private String nombre;
	private String dm;
	private List<Dnd_personaje> lista;

	public Dnd_campana(String nombre, String dm) {
		this.lista = new ArrayList<>();
		this.nombre = nombre;
		this.dm = dm;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public List<Dnd_personaje> getLista() {
		return lista;
	}

	public void setLista(List<Dnd_personaje> lista) {
		this.lista = lista;
	}

}

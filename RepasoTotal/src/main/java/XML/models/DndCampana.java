package XML.models;

import java.util.ArrayList;
import java.util.List;

public class DndCampana {
	private String nombre;
	private String dm;
	private List<DndPersonaje> lista;

	public DndCampana(String nombre, String dm) {
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

	public List<DndPersonaje> getLista() {
		return lista;
	}

	public void setLista(List<DndPersonaje> lista) {
		this.lista = lista;
	}

}

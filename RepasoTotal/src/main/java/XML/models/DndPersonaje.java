package XML.models;

import java.util.ArrayList;
import java.util.List;

public class DndPersonaje {
	private String nombre;
	private List<DndObjeto> lista;

	public DndPersonaje(String nombre) {
		this.lista = new ArrayList<>();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DndObjeto> getLista() {
		return lista;
	}

	public void setLista(List<DndObjeto> lista) {
		this.lista = lista;
	}

}

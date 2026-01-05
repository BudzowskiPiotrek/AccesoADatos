package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Duenos {
	private String nombre;
	private List<Pacientes> listaMascotas;

	public Duenos(String nombre) {
		this.listaMascotas = new ArrayList<>();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pacientes> getListaMascotas() {
		return listaMascotas;
	}

	public void setListaMascotas(List<Pacientes> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}

}

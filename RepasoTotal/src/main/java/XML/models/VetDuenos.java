package XML.models;

import java.util.ArrayList;
import java.util.List;

public class VetDuenos {
	private String nombre;
	private List<VetPacientes> listaMascotas;

	public VetDuenos(String nombre) {
		this.listaMascotas = new ArrayList<>();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<VetPacientes> getListaMascotas() {
		return listaMascotas;
	}

	public void setListaMascotas(List<VetPacientes> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}

}

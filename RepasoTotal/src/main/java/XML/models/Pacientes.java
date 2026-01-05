package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Pacientes {
	private String nombre;
	private String especie;
	private List<Visita> listaVisitas;

	public Pacientes(String nombre, String especie) {
		this.listaVisitas = new ArrayList<>();
		this.nombre = nombre;
		this.especie = especie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public List<Visita> getListaVisitas() {
		return listaVisitas;
	}

	public void setListaVisitas(List<Visita> listaVisitas) {
		this.listaVisitas = listaVisitas;
	}

}

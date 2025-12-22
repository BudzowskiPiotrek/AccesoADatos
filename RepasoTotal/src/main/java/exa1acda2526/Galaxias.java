package exa1acda2526;

import java.util.ArrayList;
import java.util.List;

public class Galaxias {
	private String nombre;
	private String tipo;
	private long numero_estrellas;
	private List<Estrellas> listaEstrellas;

	public Galaxias(String nombre, String tipo, long numero_estrellas) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.numero_estrellas = numero_estrellas;
		this.listaEstrellas = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getNumero_estrellas() {
		return numero_estrellas;
	}

	public void setNumero_estrellas(int numero_estrellas) {
		this.numero_estrellas = numero_estrellas;
	}

	public List<Estrellas> getListaEstrellas() {
		return listaEstrellas;
	}

	public void setListaEstrellas(List<Estrellas> listaEstrellas) {
		this.listaEstrellas = listaEstrellas;
	}

}

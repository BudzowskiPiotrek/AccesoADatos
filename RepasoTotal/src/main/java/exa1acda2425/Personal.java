package exa1acda2425;

import java.io.Serializable;

public class Personal implements Serializable{
	private int codigo;
	private String nombre;
	private String categoria;

	public Personal(int codigo, String nombre, String categoria) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.categoria = categoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}

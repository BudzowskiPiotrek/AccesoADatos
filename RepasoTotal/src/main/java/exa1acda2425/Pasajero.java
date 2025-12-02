package exa1acda2425;

import java.io.Serializable;

public class Pasajero implements Serializable {
	private int cod;
	private String nombre;
	private String tlf;
	private String direccion;
	private String pais;

	public Pasajero(int cod, String nombre, String tlf, String direccion, String pais) {
		this.cod = cod;
		this.nombre = nombre;
		this.tlf = tlf;
		this.direccion = direccion;
		this.pais = pais;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}

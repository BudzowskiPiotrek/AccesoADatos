package exa1acda2526;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Planetas {
	private String nombre;
	private float masa;
	private float periodo;
	private String nombreEstrella;
	private int descubridor;
	private Date fecha;
	private List<Estrellas> listaEstrellas;

	public Planetas(String nombre, float masa, float periodo, String nombreEstrella, int descubridor, Date fecha) {
		this.nombre = nombre;
		this.masa = masa;
		this.periodo = periodo;
		this.nombreEstrella = nombreEstrella;
		this.descubridor = descubridor;
		this.fecha = fecha;
		this.listaEstrellas = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getMasa() {
		return masa;
	}

	public void setMasa(float masa) {
		this.masa = masa;
	}

	public float getPeriodo() {
		return periodo;
	}

	public void setPeriodo(float periodo) {
		this.periodo = periodo;
	}

	public String getNombreEstrella() {
		return nombreEstrella;
	}

	public void setNombreEstrella(String nombreEstrella) {
		this.nombreEstrella = nombreEstrella;
	}

	public int getDescubridor() {
		return descubridor;
	}

	public void setDescubridor(int descubridor) {
		this.descubridor = descubridor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Estrellas> getListaEstrellas() {
		return listaEstrellas;
	}

	public void setListaEstrellas(List<Estrellas> listaEstrellas) {
		this.listaEstrellas = listaEstrellas;
	}

}

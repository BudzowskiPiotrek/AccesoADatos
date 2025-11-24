package peval3acda2526;

import java.util.ArrayList;
import java.util.List;

public class Especia {
	private String nombre;
	private String origenGeografico;
	private double stockGramos;
	private int intensidadPicante;
	private List<PerfilSabor> usos;

	public Especia(String nombre, String origenGeografico, double stockGramos, int intensidadPicante, PerfilSabor perfilSabor) {
		this.nombre = nombre;
		this.origenGeografico = origenGeografico;
		this.stockGramos = stockGramos;
		this.intensidadPicante = intensidadPicante;
		this.usos = new ArrayList<>();
		this.usos.add(perfilSabor);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigenGeografico() {
		return origenGeografico;
	}

	public void setOrigenGeografico(String origenGeografico) {
		this.origenGeografico = origenGeografico;
	}

	public double getStockGramos() {
		return stockGramos;
	}

	public void setStockGramos(double stockGramos) {
		this.stockGramos = stockGramos;
	}

	public int getIntensidadPicante() {
		return intensidadPicante;
	}

	public void setIntensidadPicante(int intensidadPicante) {
		this.intensidadPicante = intensidadPicante;
	}

	public List<PerfilSabor> getUsos() {
		return usos;
	}

	public void setUsos(List<PerfilSabor> usos) {
		this.usos = usos;
	}

	@Override
	public String toString() {
		return String.format("----------Nombre: %s\n----------Stock: %f| Picante: %d\n----------Usos (Tipos de cocina): %s", nombre, stockGramos,
				intensidadPicante, usos);
	}

}

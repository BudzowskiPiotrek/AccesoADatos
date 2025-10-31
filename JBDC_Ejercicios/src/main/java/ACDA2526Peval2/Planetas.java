package ACDA2526Peval2;

import java.time.LocalDate;

public class Planetas {
	private String nombre;
	private double masa;
	private double periodo_orbital;
	private String nombre_estrella_orbita;
	private int descubridor;
	private LocalDate fecha_descubrimiento;

	public Planetas(String nombre, double masa, double periodo_orbital, String nombre_estrella_orbita, int descubridor,
			LocalDate fecha_descubrimiento) {
		this.nombre = nombre;
		this.masa = masa;
		this.periodo_orbital = periodo_orbital;
		this.nombre_estrella_orbita = nombre_estrella_orbita;
		this.descubridor = descubridor;
		this.fecha_descubrimiento = fecha_descubrimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getMasa() {
		return masa;
	}

	public void setMasa(double masa) {
		this.masa = masa;
	}

	public double getPeriodo_orbital() {
		return periodo_orbital;
	}

	public void setPeriodo_orbital(double periodo_orbital) {
		this.periodo_orbital = periodo_orbital;
	}

	public String getNombre_estrella_orbita() {
		return nombre_estrella_orbita;
	}

	public void setNombre_estrella_orbita(String nombre_estrella_orbita) {
		this.nombre_estrella_orbita = nombre_estrella_orbita;
	}

	public int getDescubridor() {
		return descubridor;
	}

	public void setDescubridor(int descubridor) {
		this.descubridor = descubridor;
	}

	public LocalDate getFecha_descubrimiento() {
		return fecha_descubrimiento;
	}

	public void setFecha_descubrimiento(LocalDate fecha_descubrimiento) {
		this.fecha_descubrimiento = fecha_descubrimiento;
	}

}

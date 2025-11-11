package ACDA2526Peval2;

import java.time.LocalDate;

public class Estrellas {
	private String nombre;
	private double coord_x;
	private double coord_y;
	private double coord_z;
	private double distancia;
	private String nombre_estrella_principal;
	private String tipo;
	private String nombre_galaxia;
	private int descubridor;
	private LocalDate fecha_descubrimiento;

	public Estrellas(String nombre, double coord_x, double coord_y, double coord_z, double distancia,
			String nombre_estrella_principal, String tipo, String nombre_galaxia, int descubridor,
			LocalDate fecha_descubrimiento) {
		this.nombre = nombre;
		this.coord_x = coord_x;
		this.coord_y = coord_y;
		this.coord_z = coord_z;
		this.distancia = distancia;
		this.nombre_estrella_principal = nombre_estrella_principal;
		this.tipo = tipo;
		this.nombre_galaxia = nombre_galaxia;
		this.descubridor = descubridor;
		this.fecha_descubrimiento = fecha_descubrimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(double coord_x) {
		this.coord_x = coord_x;
	}

	public double getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(double coord_y) {
		this.coord_y = coord_y;
	}

	public double getCoord_z() {
		return coord_z;
	}

	public void setCoord_z(double coord_z) {
		this.coord_z = coord_z;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getNombre_estrella_principal() {
		return nombre_estrella_principal;
	}

	public void setNombre_estrella_principal(String nombre_estrella_principal) {
		this.nombre_estrella_principal = nombre_estrella_principal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre_galaxia() {
		return nombre_galaxia;
	}

	public void setNombre_galaxia(String nombre_galaxia) {
		this.nombre_galaxia = nombre_galaxia;
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

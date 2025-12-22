package exa1acda2526;

import java.sql.Date;

public class Estrellas {
	private String nombre;
	private float cordX;
	private float cordY;
	private float cordZ;
	private float distancia;
	private String tipo;
	private String nombre_galaxia;
	private Date fecha;
	private int descubridor;

	public Estrellas(String nombre, float cordX, float cordY, float cordZ, float distancia, String tipo,
			String nombre_galaxia, Date fecha, int descubridor) {
		super();
		this.nombre = nombre;
		this.cordX = cordX;
		this.cordY = cordY;
		this.cordZ = cordZ;
		this.distancia = distancia;
		this.tipo = tipo;
		this.nombre_galaxia = nombre_galaxia;
		this.fecha = fecha;
		this.descubridor = descubridor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCordX() {
		return cordX;
	}

	public void setCordX(float cordX) {
		this.cordX = cordX;
	}

	public float getCordY() {
		return cordY;
	}

	public void setCordY(float cordY) {
		this.cordY = cordY;
	}

	public float getCordZ() {
		return cordZ;
	}

	public void setCordZ(float cordZ) {
		this.cordZ = cordZ;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getDescubridor() {
		return descubridor;
	}

	public void setDescubridor(int descubridor) {
		this.descubridor = descubridor;
	}

}

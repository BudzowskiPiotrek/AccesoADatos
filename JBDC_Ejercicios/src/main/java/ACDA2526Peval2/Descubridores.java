package ACDA2526Peval2;

public class Descubridores {
	private int carnet_investigador;
	private String nombre;
	private String apellido;

	public Descubridores(int carnet_investigador, String nombre, String apellido) {
		this.carnet_investigador = carnet_investigador;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getCarnet_investigador() {
		return carnet_investigador;
	}

	public void setCarnet_investigador(int carnet_investigador) {
		this.carnet_investigador = carnet_investigador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}

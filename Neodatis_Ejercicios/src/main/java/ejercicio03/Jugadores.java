package ejercicio03;

public class Jugadores {
	private String nombre;
	private Paises pais;

	public Jugadores() {
	}

	public Jugadores(String nombre, Paises pais) {
		this.nombre = nombre;
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Paises getPais() {
		return pais;
	}

	public void setPais(Paises pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Jugador [Nombre: " + nombre + ", País: " + pais.getNombrepais() + "]";
	}
}

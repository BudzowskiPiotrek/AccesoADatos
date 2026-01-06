package XML.models;

public class Dnd_objeto {
	private String nombre;
	private String rareza;

	public Dnd_objeto(String nombre, String rareza) {
		this.nombre = nombre;
		this.rareza = rareza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRareza() {
		return rareza;
	}

	public void setRareza(String rareza) {
		this.rareza = rareza;
	}

}

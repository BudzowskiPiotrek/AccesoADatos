package XML.models;

class Planeta {
	private String nombre;
	private boolean habitable;

	public Planeta(String nombre, boolean habitable) {
		this.nombre = nombre;
		this.habitable = habitable;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isHabitable() {
		return habitable;
	}
}

package XML.models;

public class UniPlaneta {
	private String nombre;
	private boolean habitable;

	public UniPlaneta(String nombre, boolean habitable) {
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

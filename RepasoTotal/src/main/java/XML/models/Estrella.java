package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Estrella {
	private String nombre;
	private List<Planeta> planetas;

	public Estrella(String nombre) {
		this.nombre = nombre;
		this.planetas = new ArrayList<>();
	}

	public void addPlaneta(Planeta p) {
		planetas.add(p);
	}

	public String getNombre() {
		return nombre;
	}

	public List<Planeta> getPlanetas() {
		return planetas;
	}
}
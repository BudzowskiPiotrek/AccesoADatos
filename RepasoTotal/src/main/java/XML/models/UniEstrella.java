package XML.models;

import java.util.ArrayList;
import java.util.List;

public class UniEstrella {
	private String nombre;
	private List<UniPlaneta> planetas;

	public UniEstrella(String nombre) {
		this.nombre = nombre;
		this.planetas = new ArrayList<>();
	}

	public void addPlaneta(UniPlaneta p) {
		planetas.add(p);
	}

	public String getNombre() {
		return nombre;
	}

	public List<UniPlaneta> getPlanetas() {
		return planetas;
	}
}
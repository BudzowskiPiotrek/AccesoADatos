package XML.models;

public class Planeta {

	private String nombre;
	private float masa;
	private float periodoOrbital;
	private String nombreEstrellaOrbita;

	public Planeta(String nombre, float masa, float periodoOrbital, String nombreEstrellaOrbita) {

		this.nombre = nombre;
		this.masa = masa;
		this.periodoOrbital = periodoOrbital;
		this.nombreEstrellaOrbita = nombreEstrellaOrbita;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getMasa() {
		return masa;
	}

	public void setMasa(float masa) {
		this.masa = masa;
	}

	public float getPeriodoOrbital() {
		return periodoOrbital;
	}

	public void setPeriodoOrbital(float periodoOrbital) {
		this.periodoOrbital = periodoOrbital;
	}

	public String getNombreEstrellaOrbita() {
		return nombreEstrellaOrbita;
	}

	public void setNombreEstrellaOrbita(String nombreEstrellaOrbita) {
		this.nombreEstrellaOrbita = nombreEstrellaOrbita;
	}

}
package peval3acda2526;

public class PerfilSabor {
	private String tipoCocina;
	private String notasDominantes;
	private String maridajeIdeal;
	private boolean requiereTostadoPrevio;

	public PerfilSabor(String tipoCocina, String notasDominantes, String maridajeIdeal, boolean requiereTostadoPrevio) {
		this.tipoCocina = tipoCocina;
		this.notasDominantes = notasDominantes;
		this.maridajeIdeal = maridajeIdeal;
		this.requiereTostadoPrevio = requiereTostadoPrevio;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public void setTipoCocina(String tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public String getNotasDominantes() {
		return notasDominantes;
	}

	public void setNotasDominantes(String notasDominantes) {
		this.notasDominantes = notasDominantes;
	}

	public String getMaridajeIdeal() {
		return maridajeIdeal;
	}

	public void setMaridajeIdeal(String maridajeIdeal) {
		this.maridajeIdeal = maridajeIdeal;
	}

	public boolean isRequiereTostadoPrevio() {
		return requiereTostadoPrevio;
	}

	public void setRequiereTostadoPrevio(boolean requiereTostadoPrevio) {
		this.requiereTostadoPrevio = requiereTostadoPrevio;
	}

	@Override
	public String toString() {
		return tipoCocina;
	}

}

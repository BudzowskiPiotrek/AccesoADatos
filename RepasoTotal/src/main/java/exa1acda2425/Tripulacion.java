package exa1acda2425;

import java.io.Serializable;

public class Tripulacion implements Serializable{
	private String vueloIdentificador;
	private int personalCodigo;
	private String puesto;
	private Personal personal;

	public Tripulacion(String vueloIdentificador, int personalCodigo, String puesto, Personal personal) {
		this.vueloIdentificador = vueloIdentificador;
		this.personalCodigo = personalCodigo;
		this.puesto = puesto;
		this.personal = personal;
	}

	public String getVueloIdentificador() {
		return vueloIdentificador;
	}

	public void setVueloIdentificador(String vueloIdentificador) {
		this.vueloIdentificador = vueloIdentificador;
	}

	public int getPersonalCodigo() {
		return personalCodigo;
	}

	public void setPersonalCodigo(int personalCodigo) {
		this.personalCodigo = personalCodigo;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

}

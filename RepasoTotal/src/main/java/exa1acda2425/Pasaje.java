package exa1acda2425;

import java.io.Serializable;

public class Pasaje implements Serializable{
	private int pasajeroCod;
	private String identificador;
	private int numAsiento;
	private String clase;
	private float pvp;
	private Pasajero pasajero;

	public Pasaje(int pasajeroCod, String identificador, int numAsiento, String clase, float pvp, Pasajero pasajero) {
		this.pasajeroCod = pasajeroCod;
		this.identificador = identificador;
		this.numAsiento = numAsiento;
		this.clase = clase;
		this.pvp = pvp;
		this.pasajero = pasajero;
	}

	public int getPasajeroCod() {
		return pasajeroCod;
	}

	public void setPasajeroCod(int pasajeroCod) {
		this.pasajeroCod = pasajeroCod;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public int getNumAsiento() {
		return numAsiento;
	}

	public void setNumAsiento(int numAsiento) {
		this.numAsiento = numAsiento;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public float getPvp() {
		return pvp;
	}

	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

}
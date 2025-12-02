package exa1acda2425;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vuelo implements Serializable{
	private String identificador;
	private String aeropuertoOrigen;
	private String aeropuertoDestino;
	private String tipoVuelo;
	private Date fechaVuelo;
	private List<Pasaje> pasajes;
	private List<Tripulacion> tripulacion;

	public Vuelo(String identificador, String aeropuertoOrigen, String aeropuertoDestino, String tipoVuelo,
			Date fechaSQL) {
		this.identificador = identificador;
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
		this.tipoVuelo = tipoVuelo;
		this.fechaVuelo = fechaSQL;
		this.pasajes = new ArrayList<>();
		this.tripulacion = new ArrayList<>();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(String aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public String getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(String aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public String getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}

	public Date getFechaVuelo() {
		return fechaVuelo;
	}

	public void setFechaVuelo(Date fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
	}

	public List<Pasaje> getPasajes() {
		return pasajes;
	}

	public void setPasajes(List<Pasaje> pasajes) {
		this.pasajes = pasajes;
	}

	public List<Tripulacion> getTripulacion() {
		return tripulacion;
	}

	public void setTripulacion(List<Tripulacion> tripulacion) {
		this.tripulacion = tripulacion;
	}

}
package XML.models;

import java.util.Date;

public class Visita {
	private Date fecha;
	private String diagnostico;
	private double precio;

	public Visita(Date fecha, String diagnostico, double precio) {
		this.fecha = fecha;
		this.diagnostico = diagnostico;
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}

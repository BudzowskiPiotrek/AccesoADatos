package ACDA2526Peval1;

import java.io.Serializable;

public class Estrella implements Serializable {
	private String nombre;
	private String edad;
	private String temperatura;
	private String hidrogeno;
	private String Comentario;

	public Estrella(String nombre, String edad, String temperatura, String hidrogeno, String comentario) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.temperatura = temperatura;
		this.hidrogeno = hidrogeno;
		Comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getHidrogeno() {
		return hidrogeno;
	}

	public void setHidrogeno(String hidrogeno) {
		this.hidrogeno = hidrogeno;
	}

	public String getComentario() {
		return Comentario;
	}

	public void setComentario(String comentario) {
		Comentario = comentario;
	}

	@Override
	public String toString() {
		return "Estrella [nombre=" + nombre + ", edad=" + edad + ", temperatura=" + temperatura + ", hidrogeno="
				+ hidrogeno + ", Comentario=" + Comentario + "]";
	}

}

package XML.models;
import java.util.ArrayList;
import java.util.List;

public class Galaxia {

	private String nombre;
	private String tipo;
	private long numero;

	private List<Estrellas> listaEstrellas;

	public Galaxia(String nombre, String tipo, long numero) {
		this.listaEstrellas = new ArrayList<>();
		this.nombre = nombre;
		this.tipo = tipo;
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public List<Estrellas> getListaEstrellas() {
		return listaEstrellas;
	}

	public void setListaEstrellas(List<Estrellas> listaEstrellas) {
		this.listaEstrellas = listaEstrellas;
	}

}
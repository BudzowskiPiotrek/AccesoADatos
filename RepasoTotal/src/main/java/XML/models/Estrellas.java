package XML.models;

import java.util.ArrayList;
import java.util.List;

public class Estrellas {

	private String nombre;
	private float coordX;
	private float coordY;
	private float coordZ;
	private float distancia;
	private String estrellaPrincipal;
	private String tipo;
	private String nombreGalaxia;
	
	private List<Planeta> listaPlaneta;

	public Estrellas(String nombre, float coordX, float coordY, float coordZ, float distancia, String estrellaPrincipal,
			String tipo, String nombreGalaxia) {
		this.listaPlaneta = new ArrayList<>();
		this.nombre = nombre;
		this.coordX = coordX;
		this.coordY = coordY;
		this.coordZ = coordZ;
		this.distancia = distancia;
		this.estrellaPrincipal = estrellaPrincipal;
		this.tipo = tipo;
		this.nombreGalaxia = nombreGalaxia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCoordX() {
		return coordX;
	}

	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}

	public float getCoordY() {
		return coordY;
	}

	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}

	public float getCoordZ() {
		return coordZ;
	}

	public void setCoordZ(float coordZ) {
		this.coordZ = coordZ;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public String getEstrellaPrincipal() {
		return estrellaPrincipal;
	}

	public void setEstrellaPrincipal(String estrellaPrincipal) {
		this.estrellaPrincipal = estrellaPrincipal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreGalaxia() {
		return nombreGalaxia;
	}

	public void setNombreGalaxia(String nombreGalaxia) {
		this.nombreGalaxia = nombreGalaxia;
	}

	public List<Planeta> getListaPlaneta() {
		return listaPlaneta;
	}

	public void setListaPlaneta(List<Planeta> listaPlaneta) {
		this.listaPlaneta = listaPlaneta;
	}
	
	
}

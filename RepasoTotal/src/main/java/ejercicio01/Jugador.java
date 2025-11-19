package ejercicio01;

import java.io.Serializable;

public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;
    private String categoria;
    private Deporte deporte;

    public Jugador(String nombre, int edad, String categoria, Deporte deporte) {
        this.nombre = nombre;
        this.edad = edad;
        this.categoria = categoria;
        this.deporte = deporte;
    }


    public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public Deporte getDeporte() {
		return deporte;
	}


	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getCategoria() {
        return categoria;
    }
    
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Jugador [Nombre: " + nombre + ", Edad: " + edad + 
               ", Deporte: " + deporte.getNombre() + ", Categoría: " + categoria + "]";
    }
}
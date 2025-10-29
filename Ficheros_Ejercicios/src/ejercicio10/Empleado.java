package ejercicio10;

import java.io.Serializable;

public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String apellido;
	private double salario;

	public Empleado(int id, String apellido, double salario) {
		this.id = id;
		this.apellido = apellido;
		this.salario = salario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}

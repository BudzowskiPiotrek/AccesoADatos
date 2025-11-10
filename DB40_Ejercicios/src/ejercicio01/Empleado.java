package ejercicio01;

import java.io.Serializable;

public class Empleado implements Serializable {
	private String nombre;
	private int id;
	private double salario;
	private Departamento departamento;

	public Empleado(int id, String nombre, double salario, Departamento departamento) {
		this.id = id;
		this.nombre = nombre;
		this.salario = salario;
		this.departamento = departamento;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public double getSalario() {
		return salario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", salario=" + salario + ", departamento="
				+ (departamento != null ? departamento.getNombre() : "N/A") + "]";
	}
}

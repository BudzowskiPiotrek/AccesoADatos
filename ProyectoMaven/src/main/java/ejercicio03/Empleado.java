package ejercicio03;

public class Empleado {
	int empNo;
	String apellido;
	String profesion;
	Integer director; 
	double salario;
	double comision;
	int deptNo;

	public Empleado(int empNo, String apellido, String profesion, Integer director, double salario, Double comision,
			int deptNo) {
		this.empNo = empNo;
		this.apellido = apellido;
		this.profesion = profesion;
		this.director = director;
		this.salario = salario;
		this.comision = comision;
		this.deptNo = deptNo;
	}
}

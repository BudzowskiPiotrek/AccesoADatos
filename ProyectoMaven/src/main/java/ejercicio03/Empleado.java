package ejercicio03;

public class Empleado {
	private int empNo;
	private String apellido;
	private String profesion;
	private Integer director;
	private double salario;
	private double comision;
	private int deptNo;

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

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public Integer getDirector() {
		return director;
	}

	public void setDirector(Integer director) {
		this.director = director;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

}

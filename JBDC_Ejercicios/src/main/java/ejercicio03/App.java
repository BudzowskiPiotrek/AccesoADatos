package ejercicio03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase principal que gestiona la conexión a la base de datos, la creación de
 * la tabla Empleados y la inserción de registros de prueba.
 * 
 * @author CRISTOBAL PRIMERO DE SU NOMBRE
 * @version 1.0
 */
public class App {
	/** URL de conexión a la base de datos. */
	private static final String URL = "jdbc:mysql://localhost/ejemplo";
	/** Usuario de la base de datos. */
	private static final String USUARIO = "root";
	/** Contraseña del usuario de la base de datos. */
	private static final String CONTRASEÑA = "";
	/** Lista estática para almacenar los objetos Empleado de prueba. */
	private static final List<Empleado> empleados = new ArrayList<>();

	public static void main(String[] args) {
		crearRegistros();
		try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
			crearTabla(conexion);
			ingresarDatos(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea la tabla 'Empleados' en la base de datos. Si la tabla ya existe, captura
	 * la excepcion y muestra un mensaje de error.
	 * 
	 * @param conexion Objeto Connection a la base de datos.
	 */
	private static void crearTabla(Connection conexion) {
		try (Statement sentencia = conexion.createStatement()) {
			StringBuilder sql = new StringBuilder();
			sql.append("CREATE TABLE Empleados (\n");
			sql.append("emp_no INT PRIMARY KEY,\n");
			sql.append("apellido VARCHAR(100) NOT NULL,\n");
			sql.append("profesion VARCHAR(100) NOT NULL,\n");
			sql.append("director INT,\n");
			sql.append("fecha_alta DATE NOT NULL,\n");
			sql.append("salario DECIMAL(10, 2) NOT NULL,\n");
			sql.append("comision DECIMAL(10, 2),\n");
			sql.append("dept_no TINYINT(2) NOT NULL,\n");
			sql.append("FOREIGN KEY (dept_no) REFERENCES departamentos(dept_no),\n");
			sql.append("FOREIGN KEY (director) REFERENCES Empleados(emp_no)\n");
			sql.append(")");
			sentencia.executeUpdate(sql.toString());
			System.out.println("Tabla Empleados creada con Exito.");
		} catch (SQLException e) {
			// PARA SACAR NUMERO DE FALLO => System.out.println(e.getErrorCode());
			if (e.getErrorCode() == 1050) {
				System.err.println("ERROR: La tabla Empleados ya existe.");
			} else if (e.getErrorCode() == 1452) {
				System.err.println("ERROR: Falla la clave foranea.");
			} else {
				System.err.println("Error SQL no controlado");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Itera sobre la lista de empleados y llama al metodo para insertar cada uno.
	 * 
	 * @param conexion Objeto Connection a la base de datos.
	 */
	private static void ingresarDatos(Connection conexion) {
		for (Empleado e : empleados) {
			try {
				insertarEmpleado(conexion, e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Inserta un objeto Empleado en la tabla 'Empleados'. Realiza varias
	 * validaciones antes de la inserción (apellido, profesión, salario, existencia
	 * de departamento y director, y no duplicidad del empleado).
	 * 
	 * @param conexion Objeto Connection a la base de datos.
	 * @param e        Objeto Empleado a insertar.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	private static void insertarEmpleado(Connection conexion, Empleado e) throws SQLException {

		if (e.getApellido() == null || e.getApellido().trim().isEmpty()) {
			System.out.println("ERROR: El apellido no puede ser vacia");
			return;
		}

		if (e.getProfesion() == null || e.getProfesion().trim().isEmpty()) {
			System.out.println("ERROR: la profesion no puede ser vacia.");
			return;
		}

		if (e.getSalario() <= 0) {
			System.out.println("ERROR: El salario debe ser mayor que 0.");
			return;
		}

		if (!existeDepartamento(conexion, e.getDeptNo())) {
			System.out.println("ERROR: Departamento no existe.");
			return;
		}

		if (e.getDirector() != null && !existeEmpleado(conexion, e.getDirector())) {
			System.out.println("ERROR: Director no existe");
			return;
		}

		if (existeEmpleado(conexion, e.getEmpNo())) {
			System.out.println("ERROR: Empleado ya existe");
			return;
		}

		String sql = "INSERT INTO Empleados (emp_no, apellido, profesion, director, fecha_alta, salario, comision, dept_no) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, e.getEmpNo());
			ps.setString(2, e.getApellido());
			ps.setString(3, e.getProfesion());
			if (e.getDirector() == null) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, e.getDirector());
			}
			ps.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
			ps.setDouble(6, e.getSalario());
			ps.setDouble(7, e.getComision());
			ps.setInt(8, e.getDeptNo());

			int cantidad = ps.executeUpdate();

			if (cantidad > 0) {
				System.out.println("ÉXITO: Ingresado el empleado");
			} else {
				System.err.println("ERROR: No Ingresado el empleado");
			}

		} catch (SQLException ex) {
			System.err.println("ERROR de SQL al insertar el empleado" + ex.getMessage());
		}

	}

	/**
	 * Comprueba si un empleado con el número dado ya existe en la tabla
	 * 'Empleados'.
	 * 
	 * @param conexion Objeto Connection a la base de datos.
	 * @param empNo    El número de empleado a buscar.
	 * @return true si el empleado existe, false en caso contrario.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	private static boolean existeEmpleado(Connection conexion, int empNo) throws SQLException {
		String sql = "SELECT 1 FROM Empleados WHERE emp_no = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, empNo);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	/**
	 * Comprueba si un departamento con el número dado ya existe en la tabla
	 * 'departamentos'.
	 * 
	 * @param conexion Objeto Connection a la base de datos.
	 * @param deptNo   El número de departamento a buscar.
	 * @return true si el departamento existe, false en caso contrario.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	private static boolean existeDepartamento(Connection conexion, int deptNo) throws SQLException {
		String sql = "SELECT 1 FROM departamentos WHERE dept_no = ?";
		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, deptNo);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	/**
	 * Inicializa la lista estática 'empleados' con datos de prueba. Los datos
	 * incluyen casos que deberían fallar para probar las validaciones.
	 */
	private static void crearRegistros() {
		empleados.add(new Empleado(1, "SÁNCHEZ", "JEFAZO", null, 3000.0, 0.0, 20));
		empleados.add(new Empleado(2, "ABALOS", "SEGURIDAD", 1, 2500.0, 100.0, 20));
		empleados.add(new Empleado(3, "BARCENAS", "BANQUERO", 1, 1500.0, 0.0, 20));
		empleados.add(new Empleado(4, "", "SECRETARIA", 1, 2000.0, 0.0, 10)); // Fallara Apellido no hay
		empleados.add(new Empleado(5, "CARLOTA", "BECARIA", 1, -500.0, 0.0, 40)); // Fallara Salario negativo
	}
}

package ejercicio04y05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBConeccion {

	private static final String URL = "jdbc:mysql://localhost/UNIDAD2";
	private static final String USUARIO = "root";
	private static final String CONTRASEÑA = "";
	private static Connection con;

	public void conectar() {
		try {
			con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
			System.out.println("Exito: conectado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void desconectar() {
		con = null;
		System.out.println("Base de datos desconectada");
	}

	public int agregarProducto(Productos p) {
		String sql = "INSERT INTO PRODUCTOS ( DESCRIPCION, STOCKACTUAL, STOCKMINIMO, PVP )"
				+ "VALUES (  ? , ? , ? , ? )";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getDescripcion());
			ps.setInt(2, p.getStockActual());
			ps.setInt(3, p.getStockMinimo());
			ps.setDouble(4, p.getPvp());

			int filas = ps.executeUpdate();
			return filas;
		} catch (SQLException e) {
			System.out.println("ERROR: AL INTENTAR ANADIR");
			e.printStackTrace();
		}
		return 0;
	}

	public int agregarCliente(Cliente c) {
		String sql = "INSERT INTO CLIENTES ( NOMBRE, DIRECCION, POBLACION, TELEF, NIF )"
				+ "VALUES ( ? , ? , ? , ? , ? )";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getDireccion());
			ps.setString(3, c.getPoblacion());
			ps.setString(4, c.getTelefono());
			ps.setString(5, c.getNif());
			
			int filas = ps.executeUpdate();
			return filas;
		} catch (SQLException e) {
			System.out.println("ERROR: AL INTENTAR ANADIR");
			e.printStackTrace();
		}
		return 0;
	}
	public int agregarVenta(Ventas v) {
		String sql = "INSERT INTO VENTAS ( FECHA_VENTA, IDCLIENTE, IDPRODUCTO, CANTIDAD )"
				+ "VALUES (  ? , ? , ? , ? )";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, java.sql.Date.valueOf(v.getFecha()));
			ps.setInt(2, v.getIdCliente());
			ps.setInt(3, v.getIdProducto());
			ps.setInt(4, v.getCantidad());

			int filas = ps.executeUpdate();
			return filas;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1452 || e.getSQLState().equals("23000")) {
				System.err.println("ERROR DE CLAVE FORÁNEA");
			}else {
				System.out.println("ERROR: AL INTENTAR ANADIR");
				e.printStackTrace();
			}
			
		}
		return 0;
	}
}

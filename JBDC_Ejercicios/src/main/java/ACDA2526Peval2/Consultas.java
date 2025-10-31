package ACDA2526Peval2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {
	private Connection con;

	public Consultas(Connection con) {
		this.con = con;
	}

	public int ingresarEstrella(Estrellas e) {
		String sql = "INSERT INTO estrellas (nombre, coord_x, coord_y, coord_z, distancia, nombre_estrella_principal, tipo, nombre_galaxia, descubridor, fecha_descubrimiento)"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"; // 10
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, e.getNombre());
			ps.setDouble(2, e.getCoord_x());
			ps.setDouble(3, e.getCoord_y());
			ps.setDouble(4, e.getCoord_z());
			ps.setDouble(5, e.getDistancia());
			ps.setString(6, e.getNombre_estrella_principal());
			ps.setString(7, e.getTipo());
			ps.setString(8, e.getNombre_galaxia());
			ps.setInt(9, e.getDescubridor());
			ps.setDate(10, java.sql.Date.valueOf(e.getFecha_descubrimiento()));

			int filasAfectadas = ps.executeUpdate();

			return filasAfectadas;
		} catch (SQLException e1) {
			if (e1.getErrorCode() == 1062) {
				System.err.println("Error: Entrada duplicada");
			} else {
				e1.printStackTrace();
			}
		}
		return 0;
	}

	public int eliminarEstrella(String nombre) {
		String sql = "DELETE From estrellas WHERE nombre = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nombre);
			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	public int actualizarPlaneta(String nombre, double masaNuevo) {
		String sql = "UPDATE planetas\nSET masa = ?\nWhere nombre = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDouble(1, masaNuevo);
			ps.setString(2, nombre);

			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

	public int borrarDescubridor(int identificador) {
		String sql = "DELETE FROM descubridores\nWHERE carnet_investigador= ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, identificador);

			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas;
		} catch (SQLException e1) {
			if (e1.getErrorCode() == 1451) {
				System.out.println("Error: el registro que intentas borrar se usa en otra tabla de clave forranea");
			} else {
				e1.printStackTrace();
			}
		}
		return 0;
	}

	public void imprimir() {
		String sql = "SELECT G.nombre, G.tipo, G.numero_estrellas, E.nombre, E.tipo, E.coord_x, E.coord_y, E.coord_z, E.distancia,"
				+ " Es.elemento, Es.masa, P.nombre, P.masa, P.periodo_orbital\r\n" + "FROM galaxias G\r\n"
				+ "LEFT JOIN estrellas E\r\n" + "ON g.nombre=E.nombre_galaxia\r\n" + "LEFT JOIN espectros Es\r\n"
				+ "On es.estrella=E.nombre\r\n" + "Left JOIN Planetas P\r\n" + "On p.nombre_estrella_orbita=e.nombre";
		try (Statement st = con.createStatement(); ResultSet res = st.executeQuery(sql)) {
			while(res.next()) {
				System.out.println("galaxia nombre"+res.getString(1));
				System.out.println("galaxia tipo"+res.getString(2));
				System.out.println("galaxia numero estrellas:"+res.getLong(3));
				// no me dado tiempo otra vez sera =)
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

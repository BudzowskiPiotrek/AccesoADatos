package XML.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;

import XML.models.Estrellas;
import XML.models.Galaxia;
import XML.models.Planeta;

public class Ejercicio12 {
	private ConnectionNeodatis conNeo = new ConnectionNeodatis();
	private final String NOMBRE_BD_NEO = "VolcadoNeodatis.odb";
	private ConnectionSQL conSQL = new ConnectionSQL();
	private final String NOMBRE_BD = "practica_astronomia2";

	public boolean conversorSQLNEO() {
		String sql = "SELECT *,  g.nombre as gnombre, e.nombre as enombre, p.nombre as pnombre, g.tipo as gtipo, e.tipo as etipo FROM "
				+ "galaxias g LEFT JOIN estrellas e ON g.nombre= e.nombre_galaxia LEFT JOIN "
				+ "planetas p ON e.nombre = p.nombre_estrella_orbita ORDER BY g.nombre,e.nombre;";
		Galaxia galaxia = null;
		Estrellas estrella = null;
		Planeta planeta = null;
		String auxGalaxia = "";
		String auxEstrella = "";
		List<Galaxia> listaTotal = new ArrayList<>();

		try (Connection conn = conSQL.conectar(NOMBRE_BD); PreparedStatement ps = conn.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String actualGNombre = rs.getString("gnombre");
					if (actualGNombre != null && !actualGNombre.equals(auxGalaxia)) {
						galaxia = new Galaxia(actualGNombre, rs.getString("gtipo"), rs.getLong("numero_estrellas"));
						listaTotal.add(galaxia);

						auxGalaxia = actualGNombre;
						auxEstrella = "";
						estrella = null;
					}

					String actualENombre = rs.getString("enombre");
					if (actualENombre != null && !actualENombre.equals(auxEstrella)) {
						estrella = new Estrellas(actualENombre, rs.getFloat("coord_x"), rs.getFloat("coord_y"),
								rs.getFloat("coord_z"), rs.getFloat("distancia"),
								rs.getString("nombre_estrella_principal"), rs.getString("etipo"), actualGNombre);
						galaxia.getListaEstrellas().add(estrella);

						auxEstrella = actualENombre;
					}

					String actualPNombre = rs.getString("pnombre");
					if (actualPNombre != null && estrella != null) {
						planeta = new Planeta(actualPNombre, rs.getFloat("masa"), rs.getFloat("periodo_orbital"),
								rs.getString("nombre_estrella_orbita"));
						estrella.getListaPlaneta().add(planeta);
					}
				}

				try {
					ODB odb = conNeo.abrir(NOMBRE_BD_NEO);
					for (Galaxia galaxia2 : listaTotal) {
						odb.store(galaxia2);
					}
					odb.commit();
					odb.close();
					return true;
				} catch (ODBRuntimeException e) {
					System.out.println("Error al intentar crear neodatis, detalles del error: " + e.getMessage());
					return false;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error al realizar consulta" + e.getMessage());
		}
		return false;
	}
}

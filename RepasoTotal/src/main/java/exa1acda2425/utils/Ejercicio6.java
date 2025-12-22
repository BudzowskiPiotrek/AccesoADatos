package exa1acda2425.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;

public class Ejercicio6 {
	private static ConnectionSQL conSQL = new ConnectionSQL();

	public static void crearFicheroResultados() {
		try (Connection conn = conSQL.conectar();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("", false)))) {

			long votosTotales;

			String sqlVotosPartido = "SELECT P.nombre, SUM(V.votos) AS VotosObtenidos "
					+ "FROM votos V INNER JOIN partidos P ON V.partido_id = P.id "
					+ "GROUP BY P.nombre ORDER BY VotosObtenidos DESC;";

			ResultSet rs2 = conn.createStatement().executeQuery(sqlVotosPartido);

			bw.write("Partido;Votos;Escaños");
			bw.newLine();

			while (rs2.next()) {
				String nombrePartido = rs2.getString("nombre");
				long votosObtenidos = rs2.getLong("VotosObtenidos");

				double escañosCalculados = ((double) votosObtenidos);
				long escañosFinales = Math.round(escañosCalculados);

				String linea = String.format("%s;%d;%d", nombrePartido, votosObtenidos, escañosFinales);

				bw.write(linea);
				bw.newLine();
			}

			System.out.println("Fichero 'resultados.txt' creado con éxito.");

		} catch (Exception e) {
		}
	}
}

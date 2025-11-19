package ejercicio01;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class GestorDeportesExamen {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/BD_JUGADORES";
	private static final String USER = "Root";
	private static final String PASS = "";
	private static final String NEODATIS_DB_NAME = "JugadoresJuveniles.neodatis";

	// ---------------------------------------------------------------------
	// -------------------MAIN ---------------------------------------------
	// ---------------------------------------------------------------------

	public static void main(String[] args) {

		List<Jugador> todosLosJugadores = leerJugadoresDeFichero("Datos.txt");

		if (todosLosJugadores.isEmpty()) {
			System.out.println("No se leyeron datos del fichero.");
			return;
		}

		insertarEnBaseDeDatosRelacional(todosLosJugadores);

		consultarYGuardarEnNeodatis();

		System.out.println("-- FIN DEL PROCESO ---");
	}

	// ---------------------------------------------------------------------
	// -------------------FASE UNO PAPI ------------------------------------
	// ---------------------------------------------------------------------

	public static List<Jugador> leerJugadoresDeFichero(String nombreFichero) {
		List<Jugador> jugadores = new ArrayList<>();
		System.out.println("Leyendo fichero: " + nombreFichero);

		try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty())
					continue;

				String[] partes = linea.split(",");
				if (partes.length == 4) {
					try {
						String nombre = partes[0].trim();
						int edad = Integer.parseInt(partes[1].trim());
						String deporteNombre = partes[2].trim();
						String categoria = partes[3].trim();
						Deporte deporte = new Deporte(deporteNombre);
						Jugador jugador = new Jugador(nombre, edad, categoria, deporte);
						jugadores.add(jugador);
						System.out.println("   Leído y creado: " + jugador.getNombre());

					} catch (NumberFormatException e) {
						System.err.println("Error de formato numerico" + e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			System.err.println("ERROR al leer el fichero: " + e.getMessage());
		}
		return jugadores;
	}

	// ---------------------------------------------------------------------
	// -------------------FASE DOS PAPI ------------------------------------
	// ---------------------------------------------------------------------

	public static void insertarEnBaseDeDatosRelacional(List<Jugador> jugadores) {
		System.out.println("Conectando a BD y insertando datos...");

		String sql = "INSERT INTO JUGADORES (nombre, edad, deporte, categoria) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			int contador = 0;
			for (Jugador j : jugadores) {
				stmt.setString(1, j.getNombre());
				stmt.setInt(2, j.getEdad());
				stmt.setString(3, j.getNombre());
				stmt.setString(4, j.getCategoria());

				stmt.executeUpdate();
				contador++;
			}
			System.out.println(contador + " jugadores insertados correctamente en la BD Relacional.");

		} catch (SQLException e) {
			System.err.println("ERROR SQL durante la inserción." + e.getMessage());
		}
	}

	// ---------------------------------------------------------------------
	// -------------------FASE TRES PAPI -----------------------------------
	// ---------------------------------------------------------------------

	public static void consultarYGuardarEnNeodatis() {
		System.out.println("Consultando BD y Neodatis...");

		final String SQL_SELECT = "SELECT nombre, edad, deporte, categoria FROM JUGADORES WHERE categoria = 'Juvenil'";
		List<Jugador> jugadoresFiltrados = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL_SELECT)) {

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				int edad = rs.getInt("edad");
				String deporteNombre = rs.getString("deporte");
				String categoria = rs.getString("categoria");

				Deporte deporte = new Deporte(deporteNombre);
				Jugador jugador = new Jugador(nombre, edad, categoria, deporte);

				jugadoresFiltrados.add(jugador);
			}
			System.out.println(
					"Consulta ejecutada. Se encontraron " + jugadoresFiltrados.size() + " jugadores 'Juvenil'.");

		} catch (SQLException e) {
			System.err.println("ERROR SQL durante la consulta.");
			e.printStackTrace();
			return;
		}

		if (jugadoresFiltrados.isEmpty()) {
			System.out.println("No hay jugadores.");
			return;
		}

		System.out.println("Guardando objetos Jugador en Neodatis");

		ODB odb = null;
		try {
			odb = ODBFactory.open(NEODATIS_DB_NAME);
			int contador = 0;
			for (Jugador j : jugadoresFiltrados) {
				odb.store(j);
				contador++;
				System.out.println("Guardado: " + j.getNombre());
			}

			odb.commit();
			System.out.println(contador + " objetos guardados en Neodatis.");

		} catch (Exception e) {
			System.err.println("ERROR en Neodatis: " + e.getMessage());
		}
	}
}

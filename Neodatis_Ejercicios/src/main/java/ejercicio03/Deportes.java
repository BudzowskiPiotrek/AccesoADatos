	package ejercicio03;
	
	import java.util.ArrayList;
	import java.util.List;
	
	public class Deportes {
		private String nombre;
		private List<Jugadores> jugadores;
	
		public Deportes(String nombre) {
	
			this.nombre = nombre;
			this.jugadores = new ArrayList<>();
		}
	
		public String getNombre() {
			return nombre;
		}
	
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	
		public List<Jugadores> getJugadores() {
			return jugadores;
		}
		public void anadirJugador(Jugadores j) {
			jugadores.add(j);
		}
	
		public void setJugadores(List<Jugadores> jugadores) {
			this.jugadores = jugadores;
		}
	
		@Override
		public String toString() {
			return "Deportes [nombre=" + nombre + ", jugadores=" + jugadores + "]";
		}
	
	}

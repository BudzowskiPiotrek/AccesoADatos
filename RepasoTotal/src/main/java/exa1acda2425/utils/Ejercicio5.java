package exa1acda2425.utils;

import java.util.HashSet;

import java.util.Set;

import org.neodatis.odb.ODB;

import exa1acda2425.*;


public class Ejercicio5 {
	private ConnectionNeodatis conNeo = new ConnectionNeodatis();

	public Set<Pasajero> obtenerPasajerosFiltrados() {

        Set<Pasajero> pasajerosFiltrados = new HashSet<>();
        ODB odb = null;
        
        try {
            odb = conNeo.openDB();

        } catch (Exception e) {
             System.err.println("Error general en el Ejercicio 5: " + e.getMessage());
        } finally {
            if (odb != null) {
                try {
                    odb.close();
                } catch (Exception ex) {
                    System.err.println("Error al cerrar Neodatis: " + ex.getMessage());
                }
            }
        }
        return new HashSet<>();
    }
}

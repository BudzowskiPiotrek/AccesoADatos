package ejercicio04y05;

import java.sql.Date;
import java.time.LocalDate;

public class Ventas {
	private int idVenta;
	private LocalDate Fecha;
	private int idCliente;
	private int idProducto;
	private int cantidad;
	public Ventas(int idVenta, int idCliente, int idProducto, int cantidad) {
		super();
		this.idVenta = idVenta;
		this.Fecha = LocalDate.now();
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public LocalDate getFecha() {
		return Fecha;
	}
	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}

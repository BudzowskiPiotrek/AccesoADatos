package ejercicio04y05;

import java.time.LocalDate;

public class Ventas {
	private int idVenta;
	private LocalDate Fecha;
	private Clientes cliente;
	private Productos producto;
	private int cantidad;

	public Ventas(int idVenta, LocalDate fecha, Clientes cliente, Productos producto, int cantidad) {
		this.idVenta = idVenta;
		Fecha = fecha;
		this.cliente = cliente;
		this.producto = producto;
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

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}

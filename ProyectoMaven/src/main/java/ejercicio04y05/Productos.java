package ejercicio04y05;

public class Productos {
	private int id;
	private String descripcion;
	private int stockActual;
	private int stockMinimo;
	private double pvp;

	public Productos(int id, String descripcion, int stockActual, int stockMinimo, double pvp) {
		this.id = id;
		this.descripcion = descripcion;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.pvp = pvp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

}

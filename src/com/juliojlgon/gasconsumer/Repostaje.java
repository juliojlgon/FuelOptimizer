package com.juliojlgon.gasconsumer;

public class Repostaje {
	private int ID;
	private Double litros;
	private Double km;
	private String fecha;
	private Double precio;
	private Double consumo;

	// Constructores
	public Repostaje() {
	}

	public Repostaje(int id, double litros, double km, double p, double c,
			String fecha) {
		this.ID = id;
		this.litros = litros;
		this.km = km;
		this.fecha = fecha;
		this.consumo = c;
		this.precio = p;

	}

	public Repostaje(double litros, double km, double p, double c, String fecha) {
		this.litros = litros;
		this.km = km;
		this.fecha = fecha;
		this.consumo = c;
		this.precio = p;

	}

	// Getters
	public int getID() {
		return ID;
	}

	public Double getLitros() {
		return litros;
	}

	public Double getKm() {
		return km;
	}

	public String getFecha() {
		return fecha;
	}

	public Double getPrecio() {
		return precio;
	}

	public Double getConsumo() {
		return consumo;
	}

	// Setters
	public void setID(int iD) {
		ID = iD;
	}

	public void setLitros(Double litros) {
		this.litros = litros;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
}

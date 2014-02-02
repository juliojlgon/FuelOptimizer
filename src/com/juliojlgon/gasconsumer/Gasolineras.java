package com.juliojlgon.gasconsumer;

public class Gasolineras {
	
	private String provincia;
	private String localidad;
	private String direccion;
	private Double precio;
	private String marca;
	private String horario;
	
	public Gasolineras(String p, String l, String d, Double prize, String m, String h){
		this.provincia = p;
		this.localidad = l;
		this.direccion = d;
		this.precio = prize;
		this.marca = m;
		this.horario = h;
	}

}

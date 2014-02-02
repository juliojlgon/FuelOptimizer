package com.juliojlgon.gasconsumer;

public class Vehiculo {
	int ID;
	String nombre;
	
	//Constructores
	public Vehiculo() {
	}
	
	public Vehiculo(int id, String n){
		this.ID = id;
		this.nombre = n;		
	}
	
	public Vehiculo(String n){
		this.nombre = n;		
	}

	//Getters
	public int getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	//Setters
	public void setID(int iD) {
		ID = iD;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}

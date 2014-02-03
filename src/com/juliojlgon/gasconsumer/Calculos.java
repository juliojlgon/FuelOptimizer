package com.juliojlgon.gasconsumer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.util.Log;

public class Calculos {

	// =============== METODOS PARA HACER CALCULOS DE CONSUMO ================//

	/*
	 * Calculamos el consumo de gasolina
	 */
	public double calcularConsumo(double litros, double km) {
		double consumo = (litros * 100) / km;

		return consumo;
	}

	/*
	 * Calculamos el consumo total
	 */
	public double calcularConsumoTotal(List<Repostaje> datos) {
		double suma = 0; // Inicializamos el valor de la suma de los consumos

		for (int i = 0; i < datos.size(); i++)
			suma += datos.get(i).getConsumo();

		return suma / datos.size();
	}

	/*
	 * Calcular consumo mensual. Metemos el mes en el que estamos para comprobar
	 */
	public double calcularConsumoMensual(List<Repostaje> datos) {
		double suma = 0;
		int contador = 0;
		// Los meses en Calendar van de 0 a 11.
		int mes = Calendar.getInstance().get(Calendar.MONTH);

		for (int i = 0; i < datos.size(); i++)
			if (mes == stringToCal(datos.get(i).getFecha()).get(Calendar.MONTH)) {
				suma += datos.get(i).getConsumo();
				contador++; // Cuantos datos tenemos en el mes.
			}

		return suma / contador;

	}

	// =============== METODOS PARA HACER CALCULOS DE PRECIO ================//

	/*
	 * Calculamos el precio total
	 */
	public double calcularPrecioTotal(List<Repostaje> datos) {
		double suma = 0; // Inicializamos el valor de la suma de los consumos

		for (int i = 0; i < datos.size(); i++)
			suma += datos.get(i).getPrecio();

		return suma / datos.size();
	}

	/*
	 * Calcular precio mensual. Metemos el mes en el que estamos para comprobar
	 */

	public double calcularPrecioMensual(List<Repostaje> datos) {
		double suma = 0;
		int contador = 0;
		// Los meses en Calendar van de 0 a 11.
		int mes = Calendar.getInstance().get(Calendar.MONTH);

		for (int i = 0; i < datos.size(); i++)
			if (mes == stringToCal(datos.get(i).getFecha()).get(Calendar.MONTH)) {
				suma += datos.get(i).getPrecio();
				contador++; // Cuantos datos tenemos en el mes.
			}

		return suma / contador;

	}

	// =============== METODOS PARA HACER CALCULOS DE LITROS ================//

	/*
	 * Calculamos el litros totales
	 */
	public double calcularLitrosTotal(List<Repostaje> datos) {
		double suma = 0; // Inicializamos el valor de la suma de los consumos

		for (int i = 0; i < datos.size(); i++)
			suma += datos.get(i).getLitros();

		return suma / datos.size();
	}

	/*
	 * Calcular Litros mensual. Metemos el mes en el que estamos para comprobar
	 */

	public double calcularLitrosMensual(List<Repostaje> datos) {
		double suma = 0;
		int contador = 0;
		// Los meses en Calendar van de 0 a 11.
		int mes = Calendar.getInstance().get(Calendar.MONTH);

		for (int i = 0; i < datos.size(); i++)
			if (mes == stringToCal(datos.get(i).getFecha()).get(Calendar.MONTH)) {
				suma += datos.get(i).getLitros();
				contador++; // Cuantos datos tenemos en el mes.
			}

		return suma / contador;

	}

	// =================== METODOS AUXILIARES ===================//

	/*
	 * Convertirmos un string a un objeto tipo Calendar
	 */
	public Calendar stringToCal(String fecha) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
		try {
			cal.setTime(formater.parse(fecha));
		} catch (Exception e) {
			Log.e("stringToCal", e.toString());
		}

		return cal;
	}
}

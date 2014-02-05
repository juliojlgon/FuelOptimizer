package com.juliojlgon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juliojlgon.gasconsumer.R;

public class MensualFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_mensual, container,
				false);

		return rootView;
	}

	// ==================== METODOS PARA LA INTERFAZ======================//

	/*
	 * Actualizamos la vista con un CheckBox
	 */

	/*
	 * public void actualizarVista(View view) {
	 * 
	 * Button btnBorrar = (Button) findViewById(R.id.borrarTodoBut);
	 * RelativeLayout Layout = (RelativeLayout) findViewById(R.id.filtroCoches);
	 * boolean activado = ((CheckBox) view).isChecked();
	 * 
	 * if (activado == false) { Layout.setEnabled(false);
	 * Layout.setVisibility(View.GONE); btnBorrar.setEnabled(false);
	 * btnBorrar.setVisibility(View.INVISIBLE);
	 * RellenarLista(RellenarTodaLista(), list);
	 * 
	 * } else { Layout.setEnabled(true); Layout.setVisibility(View.VISIBLE);
	 * btnBorrar.setVisibility(View.VISIBLE); btnBorrar.setEnabled(true);
	 * RellenarLista(ObtenerCoches(numero), list);
	 * 
	 * } }
	 */

	/*
	 * Vamos al siguiente mes para cambiar vista de lista
	 */

	/*
	 * public void Siguiente(View view) { lblMes = (TextView)
	 * findViewById(R.id.lblMes); filtro = (CheckBox)
	 * findViewById(R.id.FiltroCheck); mes++; if (mes == 13) { mes = 0; }
	 * 
	 * if (filtro.isChecked()) { try { RellenarLista(ObtenerCoches(numero),
	 * list); } catch (Exception e) { Log.e("Error de lista", e.getMessage(),
	 * e); } } else { RellenarLista(RellenarTodaLista(), list); }
	 * 
	 * lblMes.setText(formatMes.format(mes));
	 * 
	 * }
	 */

	/*
	 * Vamos al mes anterior para actualizar contenido de lista
	 */

	/*
	 * public void Anterior(View view) { lblMes = (TextView)
	 * findViewById(R.id.lblMes); mes--; if (mes < 0) { mes = 12; }
	 * 
	 * if (filtro.isChecked()) { try { RellenarLista(ObtenerCoches(numero),
	 * list); } catch (Exception e) { Log.e("Error de lista", e.getMessage(),
	 * e); } } else { RellenarLista(RellenarTodaLista(), list); }
	 * 
	 * lblMes.setText(formatMes.format(mes)); }
	 */

}

package com.juliojlgon.gasconsumer;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.juliojlgon.menus.OpcionesFragment;
import com.juliojlgon.tabswipe.adapter.TabsPagerAdapter;

@SuppressLint("NewApi")
public class ActividadPrincipal extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewpager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	// Títulos de las pestañas
	private String[] tabs = { "Resumen", "Mensual", "Graficos" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_principal);

		// Inicializamos
		viewpager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewpager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Añadimos las pestañas
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	// ---------------------------------MENU-----------------------------------//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.actividad_principal, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.preferencias:
			// Single menu item is selected do something
			// Ex: launching new activity/screen or show alert message
			startActivity(new Intent(ActividadPrincipal.this,
					OpcionesFragment.class));

			return true;

		case R.id.acercade:
			// Metenemos un AlertDialog con el nombre de la aplicaicon y la
			// version.

			final AlertDialog alertDialog = new AlertDialog.Builder(
					ActividadPrincipal.this).create();

			// Añadimos el título
			alertDialog.setTitle("Acerca De..");

			// Contenido del mensaje
			alertDialog.setMessage(getResources().getString(R.string.app_name)
					+ "\n" + "Version: 3.0.0" + "\n"
					+ "Desarrollador: Julio López" + "\n"
					+ "Todos los derechos reservados");

			// Ponemos el boton
			alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							alertDialog.cancel();
						}
					});

			alertDialog.show();

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// ----------------------------------TABS--------------------------------------//
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}

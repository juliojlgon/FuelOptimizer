package com.juliojlgon.menus;


import com.juliojlgon.gasconsumer.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class OpcionesFragment extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferencias);
	}
}
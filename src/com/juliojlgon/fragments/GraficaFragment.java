package com.juliojlgon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juliojlgon.gasconsumer.R;

public class GraficaFragment extends Fragment {
	 
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.fragment_grafica, container, false);
	         
	        return rootView;
	    }

}

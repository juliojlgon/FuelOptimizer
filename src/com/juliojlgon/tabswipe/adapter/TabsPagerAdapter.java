package com.juliojlgon.tabswipe.adapter;

import com.juliojlgon.fragments.GraficaFragment;
import com.juliojlgon.fragments.MensualFragment;
import com.juliojlgon.fragments.ResumenFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	

	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // The resume fragment
            return new ResumenFragment();
        case 1:
            // Mensual fragment
            return new MensualFragment();
        case 2:
            // Grafica fragment
            return new GraficaFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        //Number of fragments we like to use.
        return 3;
    }
	

}

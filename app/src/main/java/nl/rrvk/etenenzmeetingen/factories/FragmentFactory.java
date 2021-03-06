package nl.rrvk.etenenzmeetingen.factories;

import android.content.Context;
import android.support.v4.app.Fragment;

import nl.rrvk.etenenzmeetingen.R;
import nl.rrvk.etenenzmeetingen.fragments.FragmentBuikpijn;
import nl.rrvk.etenenzmeetingen.fragments.FragmentDrinken;
import nl.rrvk.etenenzmeetingen.fragments.FragmentEten;
import nl.rrvk.etenenzmeetingen.fragments.FragmentPoep;
import nl.rrvk.etenenzmeetingen.fragments.FragmentResultaatBuikpijn;
import nl.rrvk.etenenzmeetingen.fragments.FragmentResultaatDrinken;
import nl.rrvk.etenenzmeetingen.fragments.FragmentResultaatEten;
import nl.rrvk.etenenzmeetingen.fragments.FragmentResultaatPoep;

public class FragmentFactory {

    private static FragmentFactory factory;
    private Context context;

    private FragmentFactory(Context context) {
        this.context = context;
    }

    public static FragmentFactory getInstance(Context context) {
        if (factory == null) {
            factory = new FragmentFactory(context);
        }
        return factory;
    }

    public Fragment createFragment(String item) {
        Fragment toReturn = null;
        // TODO get existing fragment with fragmentManager;
        if (equals(R.string.nav_poep, item)) {
            toReturn = new FragmentPoep();
        } else if (equals(R.string.nav_buikpijn, item)) {
            toReturn = new FragmentBuikpijn();
        } else if (equals(R.string.nav_drinken, item)) {
            toReturn = new FragmentDrinken();
        } else if (equals(R.string.nav_eten, item)) {
            toReturn = new FragmentEten();
        } else if (equals(R.string.nav_resultaten_poep, item)) {
            toReturn = new FragmentResultaatPoep();
        } else if (equals(R.string.nav_resultaten_buikpijn, item)) {
            toReturn = new FragmentResultaatBuikpijn();
        } else if (equals(R.string.nav_resultaten_eten, item)) {
            toReturn = new FragmentResultaatEten();
        } else if (equals(R.string.nav_resultaten_drinken, item)) {
            toReturn = new FragmentResultaatDrinken();
        }
        return toReturn;
    }

    private boolean equals(int resourceId, String s) {
        return context.getString(resourceId).equals(s);
    }
}

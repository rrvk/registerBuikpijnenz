package nl.rrvk.etenenzmeetingen.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nl.rrvk.etenenzmeetingen.MainActivity;
import nl.rrvk.etenenzmeetingen.R;
import nl.rrvk.etenenzmeetingen.adapter.ResultatenBuikpijnAdapter;
import nl.rrvk.etenenzmeetingen.adapter.ResultatenEtenAdapter;
import nl.rrvk.etenenzmeetingen.model.MeldingenBuikpijn;
import nl.rrvk.etenenzmeetingen.model.MeldingenEten;
import nl.rrvk.etenenzmeetingen.utils.ActivityUtils;

/**
 * Created by rvank on 9-10-2016.
 */

public class FragmentResultaatEten extends Fragment {
    private RecyclerView list;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultaat_eten, container, false);
        ActivityUtils.setActionbarTitles((MainActivity) getActivity(), R.string.nav_resultaten_eten, null);

        initList(view);
        return view;
    }

    private void initList(View view) {
        List<MeldingenEten> meldingenEten = MeldingenEten.listAll(MeldingenEten.class);
        list = (RecyclerView) view.findViewById(R.id.resultaat_eten_list);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(view.getContext());
        list.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ResultatenEtenAdapter(meldingenEten);
        list.setAdapter(mAdapter);
    }
}

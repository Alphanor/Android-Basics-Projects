package com.example.jude.romeguideapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HospitalsFragment extends Fragment {

    public HospitalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Place> hospitals = new ArrayList();

        hospitals.add(new Place(getString(R.string.fatebenefratelli), getString(R.string.fatebenefratelli_address), getString(R.string.fatebenefratelli_phone), getString(R.string.fatebenefratelli_page), R.string.fatebenefratelli_latitude, R.string.fatebenefratelli_longitude, R.drawable.fatebenefratelli,0));
        hospitals.add(new Place(getString(R.string.bambin_gesù), getString(R.string.bambin_gesù_address), getString(R.string.bambin_gesù_phone), getString(R.string.bambin_gesù_page), R.string.bambin_gesù_latitude, R.string.bambin_gesù_longitude, R.drawable.bambin_gesu,0));
        hospitals.add(new Place(getString(R.string.rome_american_hospital), getString(R.string.rome_american_hospital_address), getString(R.string.rome_american_hospital_phone), getString(R.string.rome_american_hospital_page), R.string.rome_american_hospital_latitude, R.string.rome_american_hospital_longitude, R.drawable.rome_american_hospital,0));
        hospitals.add(new Place(getString(R.string.concordia), getString(R.string.concordia_address), getString(R.string.concordia_phone), getString(R.string.concordia_page), R.string.concordia_latitude, R.string.concordia_longitude, R.drawable.concordia,0));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(),  hospitals);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;
    }
}

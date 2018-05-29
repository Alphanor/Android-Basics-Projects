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

public class MuseumsFragment extends Fragment {

    public MuseumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Place> museums = new ArrayList<>();

        museums.add(new Place(getString(R.string.vatican_museums), getString( R.string.vatican_museum_address), getString(R.string.vatican_museum_description), getString(R.string.vatican_museum_phone), getString(R.string.vatican_museum_page), getString(R.string.vatican_museum_hours), R.string.vatican_museum_latitude, R.string.vatican_museum_longitude, R.drawable.vatican_museum));
        museums.add(new Place(getString(R.string.capitoline_museums), getString(R.string.capitoline_museum_address), getString(R.string.capitoline_museum_description), getString(R.string.capitoline_museum_phone), getString(R.string.capitoline_museum_page), getString(R.string.capitoline_museum_hours), R.string.capitoline_museum_latitude, R.string.capitoline_museum_longitude, R.drawable.capitoline_museum));
        museums.add(new Place(getString(R.string.galleria_borghese), getString(R.string.galleria_borghese_address), getString(R.string.galleria_borghese_description), getString(R.string.galleria_borghese_phone), getString(R.string.galleria_borghese_page), getString(R.string.galleria_borghese_hours), R.string.galleria_borghese_latitude, R.string.galleria_borghese_longitude, R.drawable.galleria_borghese));
        museums.add(new Place(getString(R.string.national_etruscan_museum), getString(R.string.national_etruscan_museum_address), getString(R.string.national_etruscan_museum_description), getString(R.string.national_etruscan_museum_phone), getString(R.string.national_etruscan_museum_page), getString(R.string.national_etruscan_museum_hours), R.string.national_etruscan_museum_latitude, R.string.national_etruscan_museum_longitude, R.drawable.national_etruscan_museum));
        museums.add(new Place(getString(R.string.national_roman_museum), getString(R.string.national_roman_museum_address), getString(R.string.national_roman_museum_description), getString(R.string.national_roman_museum_phone), getString(R.string.national_roman_museum_page), getString(R.string.national_roman_museum_hours), R.string.national_roman_museum_latitude, R.string.national_roman_museum_longitude, R.drawable.national_roman_museum));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(),  museums);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        return rootView;

    }
}

package com.example.jude.romeguideapp;

import android.app.ActionBar;
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
import android.widget.Toolbar;

import java.util.ArrayList;

public class FamousPlacesFragment extends Fragment {

    public FamousPlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Place> famousPlaces = new ArrayList<>();

        famousPlaces.add(new Place(getString(R.string.coloseum), getString(R.string.coloseum_address), getString(R.string.coloseum_description), getString(R.string.coloseum_hours), R.string.colosseum_latitude, R.string.colosseum_longitude, R.drawable.coloseum));
        famousPlaces.add(new Place(getString(R.string.pantheon), getString(R.string.pantheon_address), getString(R.string.pantheon_description), getString(R.string.pantheon_hours), R.string.pantheon_latitude, R.string.pantheon_longitude, R.drawable.pantheon));
        famousPlaces.add(new Place(getString(R.string.trevi_fountain), getString(R.string.trevi_fountain_address), getString(R.string.trevi_fountain_description), getString(R.string.trevi_fountain_hours), R.string.pantheon_latitude, R.string.pantheon_longitude, R.drawable.trevi_fountain));
        famousPlaces.add(new Place(getString(R.string.st_peter_basilica), getString(R.string.st_peter_basilica_address), getString(R.string.st_peter_basilica_description), getString(R.string.st_peter_basilica_hours), R.string.st_peter_basilica_latitude, R.string.st_peter_basilica_longitude, R.drawable.st_peter_basilica));
        famousPlaces.add(new Place(getString(R.string.spanish_steps), getString(R.string.spanish_steps_address), getString(R.string.spanish_steps_description), getString(R.string.spanish_steps_hours), R.string.spanish_steps_latitude, R.string.spanish_steps_longitude, R.drawable.spanish_steps));
        famousPlaces.add(new Place(getString(R.string.janiculum), getString(R.string.janiculum_address), getString(R.string.janiculum_description), getString(R.string.janiculum_hours), R.string.janiculum_latitude, R.string.janiculum_longitude, R.drawable.janiculum_hill));
        famousPlaces.add(new Place(getString(R.string.campo_de_fiori), getString(R.string.campo_de_fiori_address), getString(R.string.campo_de_fiori_description), getString(R.string.campo_de_fiori_hours), R.string.campo_de_fiori_latitude, R.string.campo_de_fiori_longitude, R.drawable.campo_de_fiori));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), famousPlaces);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;

    }
}

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HotelsFragment extends Fragment {

    public HotelsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Place> hotels = new ArrayList();

        hotels.add(new Place(getString(R.string.appia_antica_resort), getString(R.string.appia_antica_resort_address), getString(R.string.appia_antica_resort_phone), getString(R.string.appia_antica_resort_page), R.string.appia_antica_resort_latitude, R.string.appia_antica_resort_longitude, R.drawable.appia_antica_resort,0));
        hotels.add(new Place(getString(R.string.portrait_roma), getString(R.string.portrait_roma_address), getString(R.string.portrait_roma_phone), getString(R.string.portrait_roma_page), R.string.portrait_roma_latitude, R.string.portrait_roma_longitude, R.drawable.portrait_roma, 0));
        hotels.add(new Place(getString(R.string.artemide), getString(R.string.artemide_address), getString(R.string.artemide_phone), getString(R.string.artemide_page), R.string.artemide_latitude, R.string.artemide_longitude, R.drawable.artemide,0));
        hotels.add(new Place(getString(R.string.the_fifteen_keys), getString(R.string.the_fifteen_keys_address), getString(R.string.the_fifteen_keys_phone), getString(R.string.the_fifteen_keys_page), R.string.the_fifteen_keys_latitude, R.string.the_fifteen_keys_longitude, R.drawable.the_fifteen_keys, 0));
        hotels.add(new Place(getString(R.string.jk_place), getString(R.string.jk_place_address), getString(R.string.jk_place_phone), getString(R.string.jk_place_page), R.string.jk_place_latitude, R.string.jk_place_longitude, R.drawable.jk_place, 0));
        hotels.add(new Place(getString(R.string.martis_palace), getString(R.string.martis_palace_address), getString(R.string.martis_palace_phone), getString(R.string.martis_palace_page), R.string.martis_palace_latitude, R.string.martis_palace_longitude, R.drawable.martis_palace, 0));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(),  hotels);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;





     }

}

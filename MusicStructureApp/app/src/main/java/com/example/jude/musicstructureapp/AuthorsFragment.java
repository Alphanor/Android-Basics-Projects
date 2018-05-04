package com.example.jude.musicstructureapp;

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


public class AuthorsFragment extends Fragment {

    public AuthorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Author> authors = new ArrayList<>();
        authors.add(new Author("Marshall", "Bruce", "23", R.drawable.eminem));
        authors.add(new Author("Justin", "Bieber", "12", R.drawable.justin_bieber));
        authors.add(new Author("Justin", "Timberlake", "8", R.drawable.justin_timberlake));
        authors.add(new Author("Robyn", "Fenty", "11", R.drawable.rihanna));
        authors.add(new Author("Maroon", "5", "16", R.drawable.maroon_5));
        authors.add(new Author("Gue", "Pequeno", "5", R.drawable.gue_pequeno));
        authors.add(new Author("Linkin", "Park", "13", R.drawable.linkin));
        authors.add(new Author("Martin", "Garrix", "9", R.drawable.martin_garrix));
        authors.add(new Author("Robbert", "De Corput", "18", R.drawable.hardwell));
        authors.add(new Author("Marshall", "Bruce", "23", R.drawable.eminem));

        AuthorAdapter adapter = new AuthorAdapter(getActivity(), authors);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), AuthorActivity.class);
                intent.putExtra("author_image", authors.get(position).getImageResourceId());
                intent.putExtra("author_name", authors.get(position).getAuthorName());
                intent.putExtra("author_surname", authors.get(position).getAuthorSurname());
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}

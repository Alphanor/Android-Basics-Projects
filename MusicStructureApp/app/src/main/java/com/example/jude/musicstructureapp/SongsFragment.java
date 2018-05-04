package com.example.jude.musicstructureapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsFragment extends Fragment {

    ViewPager viewPager;

    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list, container, false);

        final ArrayList<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Rap God", "Eminem", R.drawable.eminem_cover));
        songs.add(new Song("Spaceman", "Hardwell", R.drawable.hardwell_cover));
        songs.add(new Song("Animals", "Martin Garrix", R.drawable.martin_garrix_cover));
        songs.add(new Song("We Found Love", "Rihanna", R.drawable.rihanna_cover));
        songs.add(new Song("In The End", "Linkin Park", R.drawable.linkin_park_cover));
        songs.add(new Song("Jesus Of Suburdia", "Green Day", R.drawable.green_day_cover));
        songs.add(new Song("Bravo Ragazzo", "Gue Pequeno", R.drawable.pequeno_cover));
        songs.add(new Song("Can't Stop The Feeling", "Justin Timberlake", R.drawable.timberlake_cover));
        songs.add(new Song("Payphone", "Maroon 5", R.drawable.maroon_5_cover));
        songs.add(new Song("Love Yourself", "Eminem", R.drawable.eminem_cover));


        SongAdapter adapter = new SongAdapter(getActivity(), songs);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                viewPager.setCurrentItem(2);
                TextView songName = (TextView) getActivity().findViewById(R.id.song_name_textview);
                ImageView coverSong = (ImageView) getActivity().findViewById(R.id.cover_song);
                TextView authorName = (TextView) getActivity().findViewById(R.id.author_name_textview);
                coverSong.setImageResource(songs.get(position).getImageResourceId());
                songName.setText(songs.get(position).getSongName());
                authorName.setText(songs.get(position).getSongAuthor());


            }
        });
        return rootView;
    }


}

package com.example.jude.musicstructureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_song, parent, false);
        }

        Song currentSong = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.song_name);
        nameTextView.setText(currentSong.getSongName());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.song_author);
        authorTextView.setText(currentSong.getSongAuthor());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.song_image);

        if (currentSong.hasImage()) {
            imageView.setImageResource(currentSong.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else
            imageView.setVisibility(View.GONE);

        return listItemView;
    }
}
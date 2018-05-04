package com.example.jude.musicstructureapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AuthorAdapter extends ArrayAdapter<Author> {

    public AuthorAdapter(Context context, ArrayList<Author> authors) {
        super(context, 0, authors);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_author, parent, false);
        }

        Author currentAuthor = getItem(position);

        TextView authorNameTextView = (TextView) listItemView.findViewById(R.id.author_name);
        authorNameTextView.setText(currentAuthor.getAuthorName());

        TextView authorSurnameTextView = (TextView) listItemView.findViewById(R.id.author_surname);
        authorSurnameTextView.setText(currentAuthor.getAuthorSurname());

        TextView authorNumberAlbums = (TextView) listItemView.findViewById(R.id.author_number_albums);
        authorNumberAlbums.setText(currentAuthor.getNumberOfAlbums());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.author_image);

        if (currentAuthor.hasImage()) {

            imageView.setImageResource(currentAuthor.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);

        } else
            imageView.setVisibility(View.GONE);

        return listItemView;
    }
}

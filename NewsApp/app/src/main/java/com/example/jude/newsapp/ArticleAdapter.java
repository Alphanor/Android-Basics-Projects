package com.example.jude.newsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_list_item, parent, false);
        }

        Article article = getItem(position);

        TextView articleTitle = convertView.findViewById(R.id.article_title);

        TextView articleSection = convertView.findViewById(R.id.article_section);

        TextView articleAuthor = convertView.findViewById(R.id.author_name);

        TextView articleData = convertView.findViewById(R.id.article_data);

        articleTitle.setText(article.getTitle());

        articleSection.setText(article.getNameSection());

        articleAuthor.setText(article.getAuthor());

        String formattedDate = formatDate(article.getDataPublished());

        articleData.setText(formattedDate);

        return convertView;
    }

    private String formatDate(String date) {
        SimpleDateFormat jsonFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        try {
            Date parsedJson = jsonFormat.parse(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
            return dateFormat.format(parsedJson);
        } catch (ParseException e) {
            return null;
        }
    }
}

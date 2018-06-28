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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_list_item, parent, false);
        }

        Article article = getItem(position);

        TextView articleTitle = convertView.findViewById(R.id.article_title);

        TextView articleSection = convertView.findViewById(R.id.article_section);

        TextView articleData = convertView.findViewById(R.id.article_data);

        articleTitle.setText(article.getTitle());

        articleSection.setText(article.getNameSection());

        RelativeLayout articleView = convertView.findViewById(R.id.article_view);

        articleView.setBackgroundColor(getArticleColor(article.getNameSection()));

        Log.d("dsd", article.getNameSection());

        articleData.setText(article.getDataPublished());

        return convertView;
    }

    private int getArticleColor(String section) {
        int sectionColorId;
        switch(section) {
            case "Technology":
                sectionColorId = R.color.technology_section_color;
                break;
            case "World news":
                sectionColorId = R.color.world_news_section_color;
                break;
            case "Media":
                sectionColorId = R.color.life_and_style_section_color;
                break;
            case "GNM press office":
                sectionColorId = R.color.gnm_press_office_section_color;
                break;
            default:
                sectionColorId = R.color.default_section_color;
                break;
        }

        return sectionColorId;
    }
}

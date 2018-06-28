package com.example.jude.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {


    private static final String LOG_TAG = ArticleLoader.class.getName();

    private String url;

    public ArticleLoader(Context context, String url) {

        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {

        if (url == null) {
            return null;
        }

        List<Article> articles = QueryUtils.fetchArticleData(url);
        return articles;
    }
}

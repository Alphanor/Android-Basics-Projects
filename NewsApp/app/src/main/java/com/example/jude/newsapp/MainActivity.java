package com.example.jude.newsapp;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>>, SwipeRefreshLayout.OnRefreshListener {

    public static final String LOG_TAG = MainActivity.class.getName();

    private TextView emptyStateTextView;

    private ProgressBar loadingSpinner;

    private ArticleAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private static int loaderID = 1;

    private static final String REQUEST_URL = "http://content.guardianapis.com/search?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView articleListView = findViewById(R.id.list);

        adapter = new ArticleAdapter(this, new ArrayList<Article>());

        articleListView.setAdapter(adapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Article currentArticle = adapter.getItem(position);

                Uri earthquakeUri = Uri.parse(currentArticle.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                startActivity(websiteIntent);
            }
        });

        loadingSpinner = findViewById(R.id.loading_spinner);

        emptyStateTextView = findViewById(R.id.empty_view);

        articleListView.setEmptyView(emptyStateTextView);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            getLoaderManager().initLoader(loaderID, null, this);

        } else {

            loadingSpinner.setVisibility(View.GONE);

            emptyStateTextView.setText(R.string.no_connnection);
        }
    }


    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String orderBy = sharedPreferences.getString(getString(R.string.settings_order_by_key),  getString(R.string.settings_order_by_default));

        String pageSize = sharedPreferences.getString(getString(R.string.settings_page_size_key) , getString(R.string.settings_page_size_default));

        Uri baseUri = Uri.parse(REQUEST_URL);

        Uri.Builder builder = baseUri.buildUpon();

        builder.appendQueryParameter("q", "news");
        builder.appendQueryParameter("page-size", pageSize);
        builder.appendQueryParameter("order-by", orderBy);
        builder.appendQueryParameter("show-tags", "contributor");
        builder.appendQueryParameter("api-key", "187bdb31-e867-4bb7-8241-1f9ba40903b1");


        return new ArticleLoader(this, builder.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();

         if(id == R.id.action_settings) {
             Intent intent = new Intent(this, SettingsActivity.class);
             startActivity(intent);

             return true;
         }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {

        swipeRefreshLayout.setRefreshing(false);

        loadingSpinner.setVisibility(View.GONE);

        emptyStateTextView.setText(R.string.no_news_available);

        adapter.clear();

        if (articles != null && !articles.isEmpty()) {
            adapter.addAll(articles);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {

        adapter.clear();
    }

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(loaderID, null, this);
    }
}

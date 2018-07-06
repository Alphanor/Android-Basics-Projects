package com.example.jude.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.jude.newsapp.MainActivity.LOG_TAG;

public class QueryUtils {

    private QueryUtils() {

    }

    public static List<Article> fetchArticleData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Article> articles = extractFromJson(jsonResponse);

        return articles;
    }

    public static List<Article> extractFromJson(String newsJSON) {

        String author="";

        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        List<Article> articles = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            JSONObject response = baseJsonResponse.getJSONObject("response");

            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {

                JSONObject currentArticle = results.getJSONObject(i);

                String sectionName = currentArticle.getString("sectionName");

                String webTitle = currentArticle.getString("webTitle");

                String time = currentArticle.getString("webPublicationDate");

                String url = currentArticle.getString("webUrl");

                JSONArray tags = currentArticle.getJSONArray("tags");

                if(tags.length() == 0)
                    author = "Unknown";

                else {

                    for (int j = 0; j < tags.length(); j++) {

                        try {

                            JSONObject authorName = tags.getJSONObject(j);

                            author = authorName.getString("webTitle");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }

                Article article = new Article(webTitle, sectionName, author, time, url);

                articles.add(article);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the article JSON results", e);
        }

        return articles;
    }

    private static URL createUrl(String stringUrl) {

        Log.d(LOG_TAG, "createUrl is called...");

        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        Log.d(LOG_TAG, "makeHttpRequest is called...");

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

            else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

            } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the article JSON results.", e);
            }

            finally {
              if (urlConnection != null) {
                urlConnection.disconnect();
             }
                if (inputStream != null) {

                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        Log.d(LOG_TAG, "readFromStream is called...");

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }
}

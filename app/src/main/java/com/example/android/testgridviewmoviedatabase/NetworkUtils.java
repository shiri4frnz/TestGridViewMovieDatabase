package com.example.android.testgridviewmoviedatabase;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by shiri on 09-Apr-17.
 */

public final class NetworkUtils {
    private static final String MOVIE_DB_BASE_URL =
            "https://api.themoviedb.org/3/discover/movie";
    /*Use this URL for popular in FINAL Project instead
    * http://api.themoviedb.org/3/movie/popular?api_key= */
    private static final String KEY_PARAM = "api_key";
    private static final String SORT_PARAM = "sort_by";
    private static final String MY_KEY = "USE YOUR KEY";
    private static final String SORT_USING = "popularity.desc";

    private static final String MOVIE_DB_POSTER_PATH_BASE_URL =
            "https://image.tmdb.org/t/p/w185";

    /*Use this URL for Top Rated in FINAL Project instead
    * http://api.themoviedb.org/3/movie/top_rated?api_key= */
    private static final String SORT_USING_TOP_RATED = "vote_average.desc";
    private static final String VOTE_COUNT_GREATER_THAN = "vote_count.gte";
    private static final String GENRES_EXCLUDE = "without_genres";
    private static final String VOTE_COUNT = "75";
    private static final String GENRES = "99,10755";
    private static final String LANGUAGE_PARAM = "language";
    private static final String RATED_PARAM = "include_adult";
    private static final String VIDEO_PARAM = "include_video";
    private static final String LANGUAGE = "en-US";
    private static final String VALUE = "false";

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //Builds a URL for popular movies
    public static URL buildURL() {
        Uri builtUri = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                .appendQueryParameter(KEY_PARAM, MY_KEY)
                .appendQueryParameter(SORT_PARAM, SORT_USING)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL" + url);

        return url;
    }

    //Builds a URL for poster paths
    public static URL buildPosterPathURL(String s) {
        Uri builtPosterPathUri = Uri.parse(MOVIE_DB_POSTER_PATH_BASE_URL + s);

        URL url = null;

        try {
            url = new URL(builtPosterPathUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Poster Paths URL" + url);

        return url;
    }

    //Builds a URL for Top Rated movies
    public static URL buildTopRatedUrl() {
        Uri topRatedBuiltUri = Uri.parse(MOVIE_DB_BASE_URL).buildUpon()
                .appendQueryParameter(KEY_PARAM, MY_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, LANGUAGE)
                .appendQueryParameter(SORT_PARAM, SORT_USING_TOP_RATED)
                .appendQueryParameter(RATED_PARAM, VALUE)
                .appendQueryParameter(VIDEO_PARAM, VALUE)
                .appendQueryParameter(VOTE_COUNT_GREATER_THAN, VOTE_COUNT)
                .appendQueryParameter(GENRES_EXCLUDE, GENRES)
                .build();

        URL url = null;

        try {
            url = new URL(topRatedBuiltUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built Top Rated URL" + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

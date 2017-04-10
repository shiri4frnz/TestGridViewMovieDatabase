package com.example.android.testgridviewmoviedatabase.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shiri on 09-Apr-17.
 */

public final class MovieDBJsonUtils {
    public static String[] getMoviePathsFromJson(String searchResultsJsonStr)
            throws JSONException {
        final String MDB_RESULTS = "results";
        final String MDB_POSTER_PATH = "poster_path";

        String[] parsedMDBData = null;

        JSONObject searchResultsJson = new JSONObject(searchResultsJsonStr);

        JSONArray resultsArray = searchResultsJson.getJSONArray(MDB_RESULTS);

        parsedMDBData = new String[resultsArray.length()];

        for (int i = 0; i < resultsArray.length(); i++) {
            String moviePath;

            JSONObject resultsData = resultsArray.getJSONObject(i);

            moviePath = resultsData.getString(MDB_POSTER_PATH);

            parsedMDBData[i] = moviePath;
        }

        return parsedMDBData;
    }
}

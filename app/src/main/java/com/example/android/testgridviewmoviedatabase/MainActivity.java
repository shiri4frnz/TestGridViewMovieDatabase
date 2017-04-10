package com.example.android.testgridviewmoviedatabase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.testgridviewmoviedatabase.utilities.MovieDBJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public GridView mMovieGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieGridView = (GridView) findViewById(R.id.gv_test_movie_database);
        makeMovieDBQuery();

        //To be set up yet for passing details to MovieDetailsActivity class
        mMovieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    //Method for Building URL for popular movies
    //Then starting AsyncTask class
    private void makeMovieDBQuery() {
        URL movieDBBuiltUrl = NetworkUtils.buildURL();
        new MovieDBTask().execute(movieDBBuiltUrl);
    }

    //Method for Building URL for top rated movies
    //Then starting AsyncTask class
    private void makeMovieDBQueryTopRated() {
        URL movieDBTopRatedBuiltUrl = NetworkUtils.buildTopRatedUrl();
        new MovieDBTask().execute(movieDBTopRatedBuiltUrl);
    }


    //To get reults from JSON
    public class MovieDBTask extends AsyncTask<URL, Void, String[]> {
        @Override
        protected String[] doInBackground(URL... params) {
            URL queryUrl = params[0];
            String movieSearchResults;
            String[] moviePathResult = null;
            try {
                movieSearchResults = NetworkUtils.getResponseFromHttpUrl(queryUrl);

                moviePathResult = MovieDBJsonUtils
                        .getMoviePathsFromJson(movieSearchResults);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return moviePathResult;
        }

        @Override
        protected void onPostExecute(String[] s) {
            if (s != null) {
                mMovieGridView.setAdapter(new ImageGridAdapter(MainActivity.this, s));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gridview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemClicked = item.getItemId();

        if (menuItemClicked == R.id.action_top_rated) {
            makeMovieDBQueryTopRated();
            return true;
        } else if (menuItemClicked == R.id.action_most_popular) {
            makeMovieDBQuery();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}

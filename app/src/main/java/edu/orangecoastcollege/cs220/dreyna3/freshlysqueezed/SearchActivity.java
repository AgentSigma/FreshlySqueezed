package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView movieSearchListView;
    private MovieListAdapter mMovieListAdapter;
    private List<Movie> allMoviesList;
    private Profile userProfile;
    private EditText mSearchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieSearchListView = (ListView) findViewById(R.id.movieSearchListView);
        mSearchEditText = (EditText) findViewById(R.id.searchTermEditText);

        mSearchEditText.addTextChangedListener(movieTitleTextWatcher);
        userProfile = getIntent().getParcelableExtra("userProfile");
        allMoviesList = new ArrayList<>();
        try {
            allMoviesList = MovieJSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMovieListAdapter = new MovieListAdapter(this, R.layout.movie_list_item, allMoviesList);

        movieSearchListView.setAdapter(mMovieListAdapter);
    }
    public void toMovieDetailsActivity(View v){
        LinearLayout selected = (LinearLayout) v;
        Movie m = (Movie) selected.getTag();
        Intent toMovieDetailsIntent = new Intent(this, MovieDetailsActivity.class);
        toMovieDetailsIntent.putExtra("selectedMovie", m);
        startActivity(toMovieDetailsIntent);
    }

    public TextWatcher movieTitleTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String entry = charSequence.toString().trim().toUpperCase();

            if (entry.equals(""))
                mMovieListAdapter.addAll(allMoviesList);
            else {
                mMovieListAdapter.clear();
                for (Movie c : allMoviesList)
                    if (c.getMovieTitle().toUpperCase().contains(entry)
                            || c.getGenre().toUpperCase().contains(entry))
                        mMovieListAdapter.add(c);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}

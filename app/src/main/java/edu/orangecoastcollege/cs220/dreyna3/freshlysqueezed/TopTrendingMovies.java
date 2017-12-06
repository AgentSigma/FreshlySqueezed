package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopTrendingMovies extends AppCompatActivity {

    private ListView mTrendingListView;
    private MovieListAdapter mMovieListAdapter;
    private List<Movie> mTrendingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_trending_movies);

        mTrendingListView = (ListView) findViewById(R.id.trendingMoviesListView);
        try {
            mTrendingList = MovieJSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mTrendingList = filterTop5Movies(mTrendingList);

        mMovieListAdapter = new MovieListAdapter(this, R.layout.movie_list_item, mTrendingList);
        mTrendingListView.setAdapter(mMovieListAdapter);
    }

    private List<Movie> filterTop5Movies(List<Movie> movieList){
        List<Movie> top5 = new ArrayList<>();
        int i = 0;
        int size = movieList.size();
        // TODO: set movieList to be top5. Have top5 be a sorted list (by rating) of reviews
        while (i < 5) {
            float greatest = 0;
            int position = 0;
            for (int j = 0; j < size; j++) {
                if (movieList.get(j).getRating() > greatest)
                {
                    position = j;
                    greatest = movieList.get(j).getRating();
                }
            }
            top5.add(movieList.remove(position));
            size--;
            i++;
        }
        return top5;
    }

    public void toMovieDetailsActivity(View v){
            LinearLayout selected = (LinearLayout) v;
            Movie m = (Movie) selected.getTag();
            Intent toMovieDetailsIntent = new Intent(this, MovieDetailsActivity.class);
            toMovieDetailsIntent.putExtra("selectedMovie", m);
            startActivity(toMovieDetailsIntent);
    }
}

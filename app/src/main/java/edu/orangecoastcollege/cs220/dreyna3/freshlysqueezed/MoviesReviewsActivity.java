package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MoviesReviewsActivity extends AppCompatActivity {

    private List<Review> mReviewList;
    private DBHelper mDb;
    private TextView mTitleTextView;
    private ListView mListView;
    private ReviewListAdapter mReviewListAdapter;
    private String mMovieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_reviews);

        mTitleTextView = (TextView) findViewById(R.id.moviesReviewsTitleTextView);
        mListView = (ListView) findViewById(R.id.moviesReviewsListView);

        mDb = new DBHelper(this);
        mReviewList = mDb.getAllReivews();
        mMovieTitle = getIntent().getStringExtra("selectedMovieTitle");
        filterForMovie(mReviewList, mMovieTitle);
        mReviewListAdapter = new ReviewListAdapter(this, R.layout.review_list_item, mReviewList);

        mListView.setAdapter(mReviewListAdapter);
        mTitleTextView.setText("Reviews For:\n" + mMovieTitle);
    }

    private void filterForMovie(List<Review> filteredMoviesList, String movieTitle) {
        for (Review r : filteredMoviesList)
            if (!r.getTitle().equals(movieTitle))
                filteredMoviesList.remove(r);
    }

    public void deleteReviewClick(View v){}
}

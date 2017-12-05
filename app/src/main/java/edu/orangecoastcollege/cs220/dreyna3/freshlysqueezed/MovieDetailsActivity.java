package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie mMovie;
    private ImageView mPoster;
    private TextView mTitle;
    private TextView mGenre;
    private RatingBar mRatingBar;
    private TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mPoster = (ImageView) findViewById(R.id.moviePosterImageView);
        mTitle = (TextView) findViewById(R.id.movieDetailsTitle);
        mGenre = (TextView) findViewById(R.id.movieDetailsGenre);
        mRatingBar = (RatingBar) findViewById(R.id.movieDetailsRatingBar);
        mDescription = (TextView) findViewById(R.id.movieDetailsDescription);;

        mMovie = getIntent().getExtras().getParcelable("selectedMovie");

        String uri =
                "android.resource://edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed/drawable/"
                        + mMovie.getImageName();
        Uri mMovieImageUri = Uri.parse(uri);

        mPoster.setImageURI(mMovieImageUri);
        mTitle.setText(mMovie.getMovieTitle());
        mGenre.setText(mMovie.getGenre());
        mRatingBar.setRating(mMovie.getRating());
        mDescription.setText(mMovie.getDescription());
    }

    public void viewMoviesReviews(View view) {
        Intent toMoviesReviewsIntent = new Intent(this, MoviesReviewsActivity.class);
        toMoviesReviewsIntent.putExtra("selectedMovieTitle", mMovie.getMovieTitle());
        startActivity(toMoviesReviewsIntent);
    }
}

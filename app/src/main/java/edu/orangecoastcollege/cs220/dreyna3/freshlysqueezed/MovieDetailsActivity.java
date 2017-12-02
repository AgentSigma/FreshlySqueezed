package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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

        mMovie = getIntent().getExtras().getParcelable("selectedMovie");

        mPoster.setImageURI(Uri.parse(mMovie.getImageName()));
        mTitle.setText(mMovie.getMovieTitle());
        mGenre.setText(mMovie.getGenre());
        mRatingBar.setRating(mMovie.getRating());
        mDescription.setText(mMovie.getDescription());
    }
}

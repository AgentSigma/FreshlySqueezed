package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView movieSearchListView;
    private MovieListAdapter mMovieListAdapter;
    private List<Movie> allMoviesList;
    private Profile userProfile;
    private ImageView profileImage;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieSearchListView = (ListView) findViewById(R.id.movieSearchListView);
        profileImage=(ImageView) findViewById(R.id.searchUserProfileImageView);
        userProfile= getIntent().getParcelableExtra("userProfile");
        imageUri= Uri.parse(getIntent().getStringExtra("userImage"));
        profileImage.setImageURI(imageUri);
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
        Movie m = (Movie) v.getTag();
        Intent toMovieDetailsIntent = new Intent(this, MovieDetailsActivity.class);
        toMovieDetailsIntent.putExtra("selectedMovie", m);
        startActivity(toMovieDetailsIntent);
    }
}

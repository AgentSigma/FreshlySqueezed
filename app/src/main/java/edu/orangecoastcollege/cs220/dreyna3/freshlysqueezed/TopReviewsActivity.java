package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TopReviewsActivity extends AppCompatActivity {
    private ListView top5MoviesListView;

    private ArrayList<Review> userReviewsList;
    private Profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_reviews);

        top5MoviesListView = (ListView) findViewById(R.id.top5MoviesListView);

        userProfile = getIntent().getExtras().getParcelable("userProfile");
        //TODO: attack reviews listadapter and then filter them to the top rated movies = ez
        ReviewListAdapter movieListAdapter =
                new ReviewListAdapter(this, R.layout.review_list_item, userReviewsList);
    }
}

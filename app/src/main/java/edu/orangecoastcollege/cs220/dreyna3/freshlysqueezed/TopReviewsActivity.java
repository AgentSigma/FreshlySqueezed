package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

public class TopReviewsActivity extends AppCompatActivity {
    private ListView top5MoviesListView;

    private ArrayList<Review> userReviewsList;
    private Profile userProfile;
    private DBHelper mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_reviews);

        top5MoviesListView = (ListView) findViewById(R.id.top5MoviesListView);

        userProfile = getIntent().getExtras().getParcelable("userProfile");
        mDb = new DBHelper(this);
        userReviewsList = mDb.getAllReivews();
        filterForUsersReviews(userProfile.getUsername(), userReviewsList);
        filterTop5Reviews(userReviewsList);

        ReviewListAdapter movieListAdapter =
                new ReviewListAdapter(this, R.layout.review_list_item, userReviewsList);
        top5MoviesListView.setAdapter(movieListAdapter);
    }

    private void filterForUsersReviews(String username, List<Review> reviewList) {
        for (Review r : reviewList)
            if (!r.getAuthor().equals(username))
                reviewList.remove(r);
    }

    private void filterTop5Reviews(List<Review> reviewList){
        List<Review> top5 = new ArrayList<>();

        // TODO: set reviewList to be top5. Have top5 be a sorted list (by rating) of reviews
    }

    public void deleteReviewClick(View v){}
}

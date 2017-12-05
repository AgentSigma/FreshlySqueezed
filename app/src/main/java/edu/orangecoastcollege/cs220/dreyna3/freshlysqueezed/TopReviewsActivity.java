package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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
        userReviewsList = (ArrayList<Review>) filterTop5Reviews(userReviewsList);

        ReviewListAdapter movieListAdapter =
                new ReviewListAdapter(this, R.layout.review_list_item, userReviewsList);
        top5MoviesListView.setAdapter(movieListAdapter);
    }

    private void filterForUsersReviews(String username, List<Review> reviewList) {
        for (Review r : reviewList)
            if (!r.getAuthor().equals(username))
                reviewList.remove(r);
    }

    private List<Review> filterTop5Reviews(List<Review> reviewList){
        List<Review> top5 = new ArrayList<>();
        int i = 0;
        int size = reviewList.size();
        // TODO: set reviewList to be top5. Have top5 be a sorted list (by rating) of reviews
        while (i < 5) {
            float greatest = 0;
            int position = 0;
            for (int j = 0; j < size; j++) {
                if (reviewList.get(j).getRating() > greatest)
                {
                    position = j;
                    greatest = reviewList.get(j).getRating();
                }
            }
            top5.add(reviewList.remove(position));
            size--;
            i++;
        }
        return top5;
    }

    public void deleteReviewClick(View v){}
}

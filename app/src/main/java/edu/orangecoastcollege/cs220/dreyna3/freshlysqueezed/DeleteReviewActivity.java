package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class DeleteReviewActivity extends AppCompatActivity {

    private ListView mReviewsListView;
    private LinearLayout mListItem;
    private DBHelper mDb;
    private ReviewListAdapter mReviewListAdapter;
    private List<Review> mReviewsList;
    private Profile mUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_review);

        mReviewsListView = (ListView) findViewById(R.id.deleteReviewListView);

        mUserProfile = getIntent().getExtras().getParcelable("userProfile");
        mDb = new DBHelper(this);
        mReviewsList = mDb.getAllReivews();
        filterForUsersReviews(mUserProfile.getUsername(), mReviewsList);
        mReviewListAdapter = new ReviewListAdapter(this, R.layout.review_list_item, mReviewsList);
        mReviewsListView.setAdapter(mReviewListAdapter);
    }

    public void deleteReviewClick(View v){
        mListItem = (LinearLayout) v;
        Review reviewToDelete = (Review) v.getTag();
        for (Review r : mReviewsList){
            if (reviewToDelete.equals(r)) {
                mReviewsList.remove(r);
                mDb.deleteReview(r);
                break;
            }
        }
        mReviewListAdapter.notifyDataSetChanged();
    }

    private void filterForUsersReviews(String username, List<Review> reviewList) {
        for (Review r : reviewList)
            if (!r.getAuthor().equals(username))
                reviewList.remove(r);
    }
}

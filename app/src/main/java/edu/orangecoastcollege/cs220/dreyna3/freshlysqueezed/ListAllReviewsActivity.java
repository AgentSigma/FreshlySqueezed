package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class ListAllReviewsActivity extends AppCompatActivity {

    private DBHelper mDb;
    private ImageView mProfileImageView;
    private ListView mReviewsListView;
    private List<Review> mReviewList;
    private ReviewListAdapter mReviewListAdapter;
    private Profile mUserProfile;
    private Uri mUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_reviews);

        mProfileImageView = (ImageView) findViewById(R.id.listReviewsProfileImage);
        mReviewsListView = (ListView) findViewById(R.id.listReviewsListView);

        mUserProfile = getIntent().getExtras().getParcelable("userProfile");

        mUri = Uri.parse(mUserProfile.getImage());
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
            bitmap = Bitmap.createScaledBitmap(bitmap,
                    (int) (bitmap.getWidth()*0.3),
                    (int) (bitmap.getHeight()*0.3), true);

            mProfileImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.e("ListAllReviews", "Error getting bitmap from: " + mUri.toString(), e);
        }

        mDb = new DBHelper(this);
        mReviewList = mDb.getAllReivews();
        filterForUsersReviews(mUserProfile.getUsername(), mReviewList);
        mReviewListAdapter = new ReviewListAdapter(this, R.layout.review_list_item, mReviewList);

        mReviewsListView.setAdapter(mReviewListAdapter);
        mReviewListAdapter.notifyDataSetChanged();
    }

    private void filterForUsersReviews(String username, List<Review> reviewList) {
        for (Review r : reviewList)
            if (!r.getAuthor().equals(username))
                reviewList.remove(r);
    }

    public void deleteReviewClick(View v){}

    public void listReviewsAlphabetically(View view) {
        // TODO: Take current mReviewList, and sort alphabetically.
        // Do mReviewListAdapater.notifyDataSetChanged(); to update the list on screen

    }
}

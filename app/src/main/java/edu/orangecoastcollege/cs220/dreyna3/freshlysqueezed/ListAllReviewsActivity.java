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
import java.util.ArrayList;
import java.util.List;

public class ListAllReviewsActivity extends AppCompatActivity {

    private DBHelper mDb;
    private ImageView mProfileImageView;
    private ListView mReviewsListView;
    private List<Review> mReviewList;
    private List<Review> mFilteredReviews;
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

        if (mUserProfile.getImage()
                .equals("android.resource://edu.orangecoastcollege.cs220.dreyna3." +
                        "freshlysqueezed/drawable/default_profile_image"))
            mProfileImageView.setImageURI(Uri.parse(mUserProfile.getImage()));
        else {
            mUri = Uri.parse(mUserProfile.getImage());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        (int) (bitmap.getWidth() * 0.3),
                        (int) (bitmap.getHeight() * 0.3), true);

                mProfileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e("ListAllReviewsActivity", "Error getting bitmap from: " + mUri.toString(), e);
            }
        }

        mDb = new DBHelper(this);
        mReviewList = mDb.getAllReivews();
        mFilteredReviews = new ArrayList<>(filterForUsersReviews(mUserProfile.getUsername(), mReviewList));
        mReviewListAdapter = new ReviewListAdapter(this, R.layout.review_list_item, mFilteredReviews);

        mReviewsListView.setAdapter(mReviewListAdapter);
    }

    private List<Review> filterForUsersReviews(String username, List<Review> reviewList) {
        List<Review> filteredReviewList = new ArrayList<>();
        for (Review r : reviewList)
            if (r.getAuthor().equals(username))
                filteredReviewList.add(r);
        return filteredReviewList;
    }

    public void deleteReviewClick(View v){}

    public void listReviewsAlphabetically(View view)  {
        int size = mFilteredReviews.size();
        Review current;
        Review prev;
        int position;
        for (int i = 1; i < size; i++)
        {
            current = mFilteredReviews.get(i);
            prev = mFilteredReviews.get(i - 1);
            position = i;
            if (current.getTitle().compareTo(prev.getTitle()) < 0)
            {
                while (current.getTitle().compareTo(prev.getTitle()) < 0 && position > 1)
                {
                    mFilteredReviews.set(position, prev);
                    position--;
                    prev = mFilteredReviews.get(position - 1);
                }
                if (current.getTitle().compareTo(prev.getTitle()) < 0)
                {
                    mFilteredReviews.set(1, mFilteredReviews.get(0));
                    mFilteredReviews.set(0, current);
                }
                else
                    mFilteredReviews.set(position, current);
            }
            mReviewListAdapter.notifyDataSetChanged();
        }
    }
}

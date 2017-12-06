package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopReviewsActivity extends AppCompatActivity {
    private ListView top5MoviesListView;

    private ArrayList<Review> userReviewsList;
    private Profile userProfile;
    private DBHelper mDb;
    private ImageView mUserImage;
    private Uri mUri;
    private List<Review> mFilteredReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_reviews);

        top5MoviesListView = (ListView) findViewById(R.id.top5MoviesListView);
        mUserImage = (ImageView) findViewById(R.id.top5MoviesProfileImage);

        userProfile = getIntent().getExtras().getParcelable("userProfile");
        mDb = new DBHelper(this);
        userReviewsList = mDb.getAllReivews();

        mFilteredReviews = filterForUsersReviews(userProfile.getUsername(), userReviewsList);

        if (mFilteredReviews.size() > 1)
            mFilteredReviews = filterTop5Reviews(mFilteredReviews);

        ReviewListAdapter movieListAdapter =
                new ReviewListAdapter(this, R.layout.review_list_item, mFilteredReviews);
        top5MoviesListView.setAdapter(movieListAdapter);

        mUri = Uri.parse(userProfile.getImage());
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
            bitmap = Bitmap.createScaledBitmap(bitmap,
                    (int) (bitmap.getWidth()*0.3),
                    (int) (bitmap.getHeight()*0.3), true);

            mUserImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.e("ListAllReviews", "Error getting bitmap from: " + mUri.toString(), e);
        }
    }

    private List<Review> filterForUsersReviews(String username, List<Review> reviewList) {
        List<Review> filteredReviews = new ArrayList<>();
        for (Review r : reviewList)
            if (r.getAuthor().equals(username))
                filteredReviews.add(r);
        return filteredReviews;
    }

    private List<Review> filterTop5Reviews(List<Review> reviewList){
        List<Review> top5 = new ArrayList<>();
        int i = 0;
        int size = reviewList.size();
        // TODO: set reviewList to be top5. Have top5 be a sorted list (by rating) of reviews
        while (i < 5 && size > 0) {
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

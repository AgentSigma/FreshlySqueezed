package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReviewsMenuActivity extends AppCompatActivity {

    private Profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_menu);

        userProfile = getIntent().getExtras().getParcelable("userProfile");
    }

    public void toWriteReviewClick(View view) {
        Intent toWriteReviewIntent = new Intent(this, WriteReviewActivity.class);
        toWriteReviewIntent.putExtra("userProfile", userProfile);
        startActivity(toWriteReviewIntent);
    }

    public void toListReviewsClick(View view) {
        Intent toListReviewsIntent = new Intent(this, ListAllReviewsActivity.class);
        toListReviewsIntent.putExtra("userProfile", userProfile);
        startActivity(toListReviewsIntent);
    }

    public void toDeleteReviewsClick(View view) {
        Intent toDeleteReviewsIntent = new Intent(this, DeleteReviewActivity.class);
        toDeleteReviewsIntent.putExtra("userProfile", userProfile);
        startActivity(toDeleteReviewsIntent);
    }
}

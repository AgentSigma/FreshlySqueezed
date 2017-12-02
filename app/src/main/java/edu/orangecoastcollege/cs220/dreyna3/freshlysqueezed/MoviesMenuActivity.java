package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoviesMenuActivity extends AppCompatActivity {

    private Profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_menu);

        userProfile = getIntent().getExtras().getParcelable("userProfile");
    }

    public void toTopReviewsClick(View view) {
        Intent toTopReviewsIntent = new Intent(this, TopReviewsActivity.class);
        toTopReviewsIntent.putExtra("userProfile", userProfile);
        startActivity(toTopReviewsIntent);
    }

    public void toSearchClick(View view) {
        Intent toSearchIntent = new Intent(this, SearchActivity.class);
        toSearchIntent.putExtra("userProfile", userProfile);
        startActivity(toSearchIntent);
    }
}

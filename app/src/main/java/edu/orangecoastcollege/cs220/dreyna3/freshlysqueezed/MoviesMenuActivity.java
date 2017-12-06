package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MoviesMenuActivity extends AppCompatActivity {

    private Profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_menu);
        Intent intent= getIntent();
        userProfile= intent.getExtras().getParcelable("userProfile");

    }

    public void toTopReviewsClick(View view) {
        Intent intent= new Intent(this, TopReviewsActivity.class);
        intent.putExtra("userProfile", userProfile);

        startActivity(intent);
    }

    public void toSearchClick(View view) {
        Intent intent= new Intent(this, SearchActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    public void toTrendingClick(View view) {
        Intent intent = new Intent(this, TopTrendingMovies.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }
}

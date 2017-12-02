package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {
    private Profile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent fromIntent = getIntent();

        userProfile = fromIntent.getExtras().getParcelable("userProfile");
    }

    public void logOutClick(View view) {
        Intent toLoginIntent = new Intent(this, LoginActivity.class);
        toLoginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toLoginIntent);
    }

    public void setProfileImage(View view) {
        String uri = "android.resource://edu.orangecoastcollege.cs220.dreyna3.freshsqueezed/drawable/"
                + userProfile.getImage();
    }

    public void toReviewsClick(View view) {

    }

    public void toMoviesClick(View view) {

    }
    // SDfsdfsd
}

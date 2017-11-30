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
        finish();
    }

    public void searchClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void setProfileImage(View view) {

    }
    // SDfsdfsd
}

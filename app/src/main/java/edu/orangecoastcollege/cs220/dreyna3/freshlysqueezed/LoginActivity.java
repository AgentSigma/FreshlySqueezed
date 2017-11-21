package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signInClick(View view) {
        startActivity(new Intent(this, MainMenuActivity.class));
    }
}

package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed.R.id.passwordEditText;

public class LoginActivity extends AppCompatActivity {

    private ArrayList<Profile> allProfilesList;
    private DBHelper db;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText= (EditText) findViewById(R.id.passwordEditText);
        db = new DBHelper(this);

        // db.deleteDatabaseDEBUG();

        allProfilesList = db.getAllProfiles();
    }

    public void signInClick(View view) {
        String usernameText = usernameEditText.getText().toString();
        String passwordText = passwordEditText.getText().toString();
        boolean loginSuccess = false;

        if (usernameEditText.getText().toString().equals("")
                || passwordEditText.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Please enter any missing fields", Toast.LENGTH_SHORT).show();
            usernameEditText.setError("Required");
            passwordEditText.setError("Required");
        } else {
            for (Profile p : allProfilesList) {
                if (p.getUsername().equals(usernameText) && p.getPassword().equals(passwordText)) {
                    Intent intent = new Intent(this, MainMenuActivity.class);
                    intent.putExtra("userProfile", p);
                    loginSuccess = true;
                    resetViewText();
                    Toast.makeText(this, "Welcome " + p.getUsername() + "!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
            if (!loginSuccess) {
                resetViewText();
                Toast.makeText(LoginActivity.this, R.string.incorrect_input_Toast, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void resetViewText() {
        usernameEditText.setText("");
        passwordEditText.setText("");
    }

    public void createAccountClick(View view) {
        String usernameText = usernameEditText.getText().toString();
        String passwordText = passwordEditText.getText().toString();
        if (usernameText.equals("") || passwordText.equals("")){
            Toast.makeText(this, "Please enter any missing fields", Toast.LENGTH_SHORT).show();
            usernameEditText.setError("Required");
            passwordEditText.setError("Required");
            return;
        }

        Profile newProfile = new Profile(usernameText, passwordText);
        boolean isUnique = false;

        // If no accounts on DB
        if (allProfilesList.size() == 0){
            isUnique = true;
        }
        // Else check against all accounts for unique username
        else {
            for (Profile p : allProfilesList) {
                if (p.getUsername().equals(usernameText)) {
                    Toast.makeText(this, "Username already in use. Please try another.", Toast.LENGTH_SHORT).show();
                    usernameEditText.setText("");
                    usernameEditText.requestFocus();
                    break;
                } else {
                    isUnique = true;
                    resetViewText();
                    break;
                }
            }
        }
        if (isUnique){
            db.addProfile(newProfile);
            Intent toMainMenuIntent = new Intent(this, MainMenuActivity.class);
            toMainMenuIntent.putExtra("userProfile", newProfile);
            Toast.makeText(this, "Created new account: " + newProfile.getUsername(), Toast.LENGTH_SHORT).show();
            startActivity(toMainMenuIntent);
        }
    }

}

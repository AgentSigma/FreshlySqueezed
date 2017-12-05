package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class WriteReviewActivity extends AppCompatActivity {

    private DBHelper mDb;
    private EditText mTitleEditText;
    private RatingBar mRatingBar;
    private EditText mReviewEditText;
    private Profile mUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        mTitleEditText = (EditText) findViewById(R.id.writeReviewTitleEditText);
        mRatingBar = (RatingBar) findViewById(R.id.writeReviewRatingBar);
        mReviewEditText = (EditText) findViewById(R.id.writeReviewTextEditText);

        mDb = new DBHelper(this);
        mUserProfile = getIntent().getExtras().getParcelable("userProfile");
    }

    public void submitReviewClick(View view) {
        if (mTitleEditText.getText().toString().equals("")
                || mReviewEditText.getText().toString().equals(""))
            Toast.makeText(this, "Please enter any missing fields", Toast.LENGTH_SHORT).show();
        else{
            Review newReview = new Review(
                    mUserProfile.getUsername(),
                    mReviewEditText.getText().toString().trim(),
                    mTitleEditText.getText().toString().trim(),
                    mRatingBar.getRating()
            );

            mDb.addReview(newReview);
            Toast.makeText(this, "Review has been submitted to the database", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.IOException;

public class WriteReviewActivity extends AppCompatActivity {

    private DBHelper mDb;
    private EditText mTitleEditText;
    private RatingBar mRatingBar;
    private EditText mReviewEditText;
    private Profile mUserProfile;
    private ImageView mUserImage;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        mTitleEditText = (EditText) findViewById(R.id.writeReviewTitleEditText);
        mRatingBar = (RatingBar) findViewById(R.id.writeReviewRatingBar);
        mReviewEditText = (EditText) findViewById(R.id.writeReviewTextEditText);
        mUserImage = (ImageView) findViewById(R.id.writeReviewProfileImage);

        mDb = new DBHelper(this);
        mUserProfile = getIntent().getExtras().getParcelable("userProfile");

        if (mUserProfile.getImage()
                .equals("android.resource://edu.orangecoastcollege.cs220.dreyna3." +
                        "freshlysqueezed/drawable/default_profile_image"))
            mUserImage.setImageURI(Uri.parse(mUserProfile.getImage()));
        else {
            mUri = Uri.parse(mUserProfile.getImage());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        (int) (bitmap.getWidth() * 0.3),
                        (int) (bitmap.getHeight() * 0.3), true);

                mUserImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e("MainMenuActivity", "Error getting bitmap from: " + mUri.toString(), e);
            }
        }
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

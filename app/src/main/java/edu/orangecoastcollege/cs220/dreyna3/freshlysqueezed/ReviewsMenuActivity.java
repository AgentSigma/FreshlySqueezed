package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class ReviewsMenuActivity extends AppCompatActivity {

    private Profile userProfile;
    private Uri mUri;
    private ImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_menu);

        userImage = (ImageView) findViewById(R.id.reviewMenuProfileImage);

        userProfile = getIntent().getExtras().getParcelable("userProfile");

        if (userProfile.getImage()
                .equals("android.resource://edu.orangecoastcollege.cs220.dreyna3." +
                        "freshlysqueezed/drawable/default_profile_image"))
            userImage.setImageURI(Uri.parse(userProfile.getImage()));
        else {
            mUri = Uri.parse(userProfile.getImage());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        (int) (bitmap.getWidth() * 0.3),
                        (int) (bitmap.getHeight() * 0.3), true);

                userImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e("ReviewsMenuActivity", "Error getting bitmap from: " + mUri.toString(), e);
            }
        }
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

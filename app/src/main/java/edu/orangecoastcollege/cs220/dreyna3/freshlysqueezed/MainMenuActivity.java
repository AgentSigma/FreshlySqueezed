package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private static final int GRANTED= PackageManager.PERMISSION_GRANTED;
    private static final int DENIED=PackageManager.PERMISSION_DENIED;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Profile mUserProfile;
    private Uri imageUri;
    private ImageView mProfileImageView;
    private Uri mUri;
    private DBHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mProfileImageView = (ImageView) findViewById(R.id.profileImageViewMainMenu);

        mDb = new DBHelper(this);
        Intent fromIntent = getIntent();
        mUserProfile = fromIntent.getExtras().getParcelable("userProfile");

        if (mUserProfile.getImage()
                .equals("android.resource://edu.orangecoastcollege.cs220.dreyna3." +
                        "freshlysqueezed/drawable/default_profile_image"))
            mProfileImageView.setImageURI(Uri.parse(mUserProfile.getImage()));
        else {
            mUri = Uri.parse(mUserProfile.getImage());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        (int) (bitmap.getWidth() * 0.3),
                        (int) (bitmap.getHeight() * 0.3), true);

                mProfileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e("MainMenuActivity", "Error getting bitmap from: " + mUri.toString(), e);
            }
        }
    }

    public void logOutClick(View view) {
        Intent toLoginIntent = new Intent(this, LoginActivity.class);
        toLoginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toLoginIntent);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!= null)
        {
            //data= data from the Gallery Intent(the image
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        (int) (bitmap.getWidth()*0.3),
                        (int) (bitmap.getHeight()*0.3), true); // Scale down to 1/3 of resolution

                mProfileImageView.setImageBitmap(bitmap);
                mUserProfile.setImageName(imageUri.toString());
                mDb.updateProfile(mUserProfile);
                Toast.makeText(this, "Image set to " + imageUri.toString(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Log.e("MainMenuActivity", "Error getting bitmap from: " + mUri.toString(), e);
            }
        }
        else
        {
            Toast.makeText(MainMenuActivity.this, "No profile image selected", Toast.LENGTH_LONG).show();
        }
    }

    public void setProfileImage(View view) {
        List<String> permissionList= new ArrayList<>();
        // check each permission individually
        int hasCameraPerm= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(hasCameraPerm==DENIED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        int hasReadPerm= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(hasReadPerm==DENIED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        int hasWritePerm= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(hasWritePerm==DENIED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(permissionList.size()>0)
        {
            //convert permsList ino an array
            String[] permsArray= new String[permissionList.size()];
            permissionList.toArray(permsArray);
            //ask
            //make a code number for third area, send
            ActivityCompat.requestPermissions(this, permsArray,1337 );


        }
        boolean takePic=false;
        //make sure we have all permissions then start up the image Gallery:
        if(hasCameraPerm== GRANTED && hasReadPerm==GRANTED && hasWritePerm==GRANTED)
        {
            //lets open up gallery
            Intent toGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(toGalleryIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    public void toReviewsClick(View view) {
        Intent toReviewIntent = new Intent(this, ReviewsMenuActivity.class);
        toReviewIntent.putExtra("userProfile", mUserProfile);
        startActivity(toReviewIntent);
    }

    public void toMoviesClick(View view) {
        Intent intent= new Intent(this, MoviesMenuActivity.class);
        intent.putExtra("userProfile", mUserProfile);
        startActivity(intent);
    }
}

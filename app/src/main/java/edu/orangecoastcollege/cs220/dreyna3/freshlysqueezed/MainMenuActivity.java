package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private static final int GRANTED= PackageManager.PERMISSION_GRANTED;
    private static final int DENIED=PackageManager.PERMISSION_DENIED;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Profile userProfile;
    private Uri imageUri;
    private ImageView profileImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent fromIntent = getIntent();
        userProfile = fromIntent.getExtras().getParcelable("userProfile");

        profileImageView=(ImageView) findViewById(R.id.profileImageViewMainMenu) ;
        String uri = "android.resource://edu.orangecoastcollege.cs220.dreyna3.freshsqueezed/drawable/"
                + userProfile.getImage();
        imageUri=getUriFromResource(this,R.drawable.default_profile_image);

        profileImageView.setImageURI(imageUri);
    }
    public static Uri getUriFromResource(Context context, int resID)
    {
        Resources res= context.getResources();
        //Build a String in the form:
        //android.resource://edu.orangecoastcollege.cs273.petprotector
        String uri= ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+ res.getResourcePackageName(resID)+ "/"
                + res.getResourceTypeName(resID)+"/"
                +res.getResourceEntryName(resID);
        // parse the string in o rder to construc a URI
        return Uri.parse(uri);

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
            imageUri= data.getData();
            profileImageView.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(MainMenuActivity.this, "image could not be pulled from camera", Toast.LENGTH_LONG).show();
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
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                takePic=true;
            }
            // start activity for aour results the picture that the user selected
            if(takePic)
                startActivityForResult(takePictureIntent,1);

        }

    }

    public void toReviewsClick(View view) {
        Intent toReviewIntent = new Intent(this, ReviewsMenuActivity.class);
        toReviewIntent.putExtra("userProfile", userProfile);
        toReviewIntent.putExtra("userImage", imageUri.toString());
        startActivity(toReviewIntent);
    }

    public void toMoviesClick(View view) {
        Intent intent= new Intent(this, MoviesMenuActivity.class);
        intent.putExtra("userProfile", userProfile);
        intent.putExtra("userImage", imageUri.toString());
        startActivity(intent);
    }
}

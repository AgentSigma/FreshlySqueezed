package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by dreyna3 on 11/21/2017.
 * ALL DATABASE READ/WRITE OPERATIONS GO HERE
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "ProfileReviewDB";

    private Context mContext;

    private final String KEY_FIELD_ID = "_id";
    private final String DATABASE_TABLE = "Profiles";
    private final String FIELD_USERNAME = "Username";
    private final String FIELD_PASSWORD = "Password";
    private final String FIELD_MOVIESTRING = "MovieString";
    private final String FIELD_IMAGENAME = "ImageName";

    //REVIEW DATABASE
    private final String KEY_FIELD_REVIEWID = "_id";
    private final String DATABASE_REVIEWTABLE = "Reviews";
    private final String FIELD_REVIEWUSERNAME = "Author";
    private final String FIELD_REVIEW = "review";
    private final String FIELD_REVIEWMOVIETITLE= "Title";
    private final String FIELD_REVIEWRATING = "Rating";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE " + DATABASE_REVIEWTABLE + "("
                + KEY_FIELD_REVIEWID + " INTEGER PRIMARY KEY, "
                + FIELD_REVIEWUSERNAME + " TEXT, "
                + FIELD_REVIEW + " TEXT, "
                + FIELD_REVIEWMOVIETITLE + " TEXT, "
                + FIELD_REVIEWRATING + " REAL)";
        sqLiteDatabase.execSQL(table);

         table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_USERNAME + " TEXT, "
                + FIELD_PASSWORD + " TEXT, "
                + FIELD_MOVIESTRING + " TEXT, "
                + FIELD_IMAGENAME + " TEXT)";
        sqLiteDatabase.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_REVIEWTABLE);
        onCreate(sqLiteDatabase);
    }

    // FUNCTIONS (GetAll Reviews, Insert, Delete, Update, etc)
    public void addProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_USERNAME, profile.getUsername());
        values.put(FIELD_PASSWORD, profile.getPassword());
        values.put(FIELD_IMAGENAME, profile.getImage());

        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    /**
     * Returns all profiles in an ArrayList<Profile>
     */
    public ArrayList<Profile> getAllProfiles(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Profile> profilesList = new ArrayList<>();

        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{
                        KEY_FIELD_ID,
                        FIELD_USERNAME,
                        FIELD_PASSWORD,
                        FIELD_IMAGENAME
                }, null, null, null, null, null
        );

        if (cursor.moveToNext()){
            do{
                Profile profile = new Profile(
                        cursor.getInt(0), // id
                        cursor.getString(1), // username
                        cursor.getString(2), // password
                        cursor.getString(3) // imageName
                );
                profilesList.add(profile);

            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return profilesList;
    }

    public void deleteDatabaseDEBUG(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.delete(DATABASE_REVIEWTABLE, null, null);
    }



    // REVIEW DATABASE METHODS

    public ArrayList<Review> getAllReivews(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Review> reviewList = new ArrayList<>();

        Cursor cursor = db.query(
                DATABASE_REVIEWTABLE,
                new String[]{
                        KEY_FIELD_REVIEWID,
                        FIELD_REVIEWUSERNAME,
                        FIELD_REVIEW,
                        FIELD_REVIEWMOVIETITLE,
                        FIELD_REVIEWRATING
                }, null, null, null, null, null
        );

        if (cursor.moveToNext()){
            do{
                Review review = new Review(

                        cursor.getInt(0), // id
                        cursor.getString(1), // Author
                        cursor.getString(2), // title
                        cursor.getFloat(3), // review rating
                        cursor.getString(4) // the review
                );
                reviewList.add(review);

            } while (cursor.moveToNext());
        }

        return reviewList;
    }
}

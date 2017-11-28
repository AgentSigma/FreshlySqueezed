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
    static final String DATABASE_NAME = "ProfilesDB";

    private Context mContext;

    private final String KEY_FIELD_ID = "_id";
    private final String DATABASE_TABLE = "Profiles";
    private final String FIELD_USERNAME = "Username";
    private final String FIELD_PASSWORD = "Password";
    private final String FIELD_MOVIESTRING = "MovieString";
    private final String FIELD_IMAGENAME = "ImageName";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
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
        onCreate(sqLiteDatabase);
    }

    // FUNCTIONS (GetAll Reviews, Insert, Delete, Update, etc)
    public void addProfile(Profile profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_USERNAME, profile.getUsername());
        values.put(FIELD_PASSWORD, profile.getPassword());
        values.put(FIELD_MOVIESTRING, profile.getReviews());
        values.put(FIELD_IMAGENAME, profile.getImageName());

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
                        FIELD_MOVIESTRING,
                        FIELD_IMAGENAME
                }, null, null, null, null, null
        );

        if (cursor.moveToNext()){
            do{
                Profile profile = new Profile(
                        cursor.getInt(0), // id
                        cursor.getString(1), // username
                        cursor.getString(2), // password
                        cursor.getString(3), // movie string
                        cursor.getString(4) // image name
                );
                profilesList.add(profile);

            } while (cursor.moveToNext());
        }

        return profilesList;
    }

    /**
     * Returns all reviews in an arrayList<String>
     * @return an ArrayList of all profile's movieString s
     */
    public ArrayList<String> getAllReviews(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> allReviewsList = new ArrayList<>();

        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{
                        FIELD_MOVIESTRING
                }, null, null, null, null, null
        );

        if (cursor.moveToFirst()){
            do{
                allReviewsList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        return allReviewsList;
    }

}

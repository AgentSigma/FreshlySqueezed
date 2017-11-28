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
    private final int KEY_FIELD_ID = -1;
    private final String DATABASE_TABLE = "Profiles";
    private final String FIELD_USERNAME = "Username";
    private final String FIELD_PASSWORD = "Password";
    private final String FIELD_MOVIESTRING = "MovieString";
    private final String FIELD_IMAGENAME = "ImageName";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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
     * Returns a null profile if username and password incorrect, else, returns a profile
     * @param username user-entered username
     * @param password user-entered password
     * @return profile object
     */
    public Profile getProfile(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Profile profile = null;
        boolean profileFound = false;

        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{
                        FIELD_USERNAME,
                        FIELD_PASSWORD,
                        FIELD_MOVIESTRING,
                        FIELD_IMAGENAME
                }, null, null, null, null, null
        );
        if (cursor.moveToFirst()){

            do{
                profile = new Profile(
                        cursor.getString(0), // Username
                        cursor.getString(1), // Password
                        cursor.getString(2), // MovieString
                        cursor.getString(3) // ImageName
                );

                if (username.equals(profile.getUsername())
                        && password.equals( profile.getPassword()))
                    profileFound = true;

            }while(cursor.moveToNext() || !profileFound);
        }
        return profile;
    }

}

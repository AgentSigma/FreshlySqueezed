package edu.orangecoastcollege.cs220.dreyna3.freshlysqueezed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dreyna3 on 11/21/2017.
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

    // FUNCTIONS (Get Reviews, Insert, Delete, Update, etc)

}

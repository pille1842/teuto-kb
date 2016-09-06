package de.erixpage.teuto_kb;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Eric on 05.09.2016.
 */
public class SongDbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG             = SongDbHelper.class.getSimpleName();

    public static final String DB_NAME              = "teutokb.db";
    public static final int DB_VERSION              = 4;

    public static final String TABLE_SONGS          = "songs";

    public static final String COLUMN_ID            = "_id";
    public static final String COLUMN_NUMBER        = "number";
    public static final String COLUMN_PAGE          = "page";
    public static final String COLUMN_TITLE         = "title";
    public static final String COLUMN_BEGINNING     = "beginning";
    public static final String COLUMN_TEXT          = "text";
    public static final String COLUMN_TONALITY      = "tonality";
    public static final String COLUMN_AUTHOR_MELODY = "author_melody";
    public static final String COLUMN_AUTHOR_TEXT   = "author_text";
    public static final String COLUMN_YEAR_MELODY   = "year_melody";
    public static final String COLUMN_YEAR_TEXT     = "year_text";
    public static final String COLUMN_NOTE          = "note";

    public static final String SQL_CREATE           = "CREATE TABLE " + TABLE_SONGS +
            "(" + COLUMN_ID            + " INTEGER PRIMARY KEY AUTOINCREMENT, "     +
                  COLUMN_NUMBER        + " INTEGER, "                               +
                  COLUMN_PAGE          + " INTEGER, "                               +
                  COLUMN_TITLE         + " VARCHAR(100), "                          +
                  COLUMN_BEGINNING     + " VARCHAR(100), "                          +
                  COLUMN_TEXT          + " TEXT, "                                  +
                  COLUMN_TONALITY      + " VARCHAR(10), "                           +
                  COLUMN_AUTHOR_MELODY + " VARCHAR(50), "                           +
                  COLUMN_AUTHOR_TEXT   + " VARCHAR(50), "                           +
                  COLUMN_YEAR_MELODY   + " INTEGER, "                               +
                  COLUMN_YEAR_TEXT     + " INTEGER, "                               +
                  COLUMN_NOTE          + " TEXT)";

    private final Context fContext;

    public SongDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        fContext = context;
        Log.d(LOG_TAG, "DbHelper has created the database " + getDatabaseName() + ".");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Creating table by executing: " + SQL_CREATE);
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Error creating table: " + ex.getMessage());
        }
        // Add default records to songs table
        ContentValues _Values = new ContentValues();
        Resources res = fContext.getResources();

        XmlResourceParser _xml = res.getXml(R.xml.songs_records);
        try {
            int eventType = _xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if ((eventType == XmlPullParser.START_TAG) && (_xml.getName().equals("record"))) {
                    String _Number = _xml.getAttributeValue(null, COLUMN_NUMBER);
                    String _Page = _xml.getAttributeValue(null, COLUMN_PAGE);
                    String _Title = _xml.getAttributeValue(null, COLUMN_TITLE);
                    String _Beginning = _xml.getAttributeValue(null, COLUMN_BEGINNING);
                    String _Tonality = _xml.getAttributeValue(null, COLUMN_TONALITY);
                    String _Author_Melody = _xml.getAttributeValue(null, COLUMN_AUTHOR_MELODY);
                    String _Author_Text = _xml.getAttributeValue(null, COLUMN_AUTHOR_TEXT);
                    String _Year_Melody = _xml.getAttributeValue(null, COLUMN_YEAR_MELODY);
                    String _Year_Text = _xml.getAttributeValue(null, COLUMN_YEAR_TEXT);
                    String _Note = _xml.getAttributeValue(null, COLUMN_NOTE);
                    String _Text = _xml.getText();
                    _Values.put(COLUMN_NUMBER, _Number);
                    _Values.put(COLUMN_PAGE, _Page);
                    _Values.put(COLUMN_TITLE, _Title);
                    _Values.put(COLUMN_BEGINNING, _Beginning);
                    _Values.put(COLUMN_TONALITY, _Tonality);
                    _Values.put(COLUMN_AUTHOR_MELODY, _Author_Melody);
                    _Values.put(COLUMN_AUTHOR_TEXT, _Author_Text);
                    _Values.put(COLUMN_YEAR_MELODY, _Year_Melody);
                    _Values.put(COLUMN_YEAR_TEXT, _Year_Text);
                    _Values.put(COLUMN_NOTE, _Note);
                    _Values.put(COLUMN_TEXT, _Text);
                    Log.d(LOG_TAG, "Inserting new record, song number " + _Number);
                    db.insert(TABLE_SONGS, null, _Values);
                }
                eventType = _xml.next();
            }
        }
        catch (XmlPullParserException ex) {
            Log.e(LOG_TAG, "Error parsing XML source: " + ex.getMessage(), ex);
        }
        catch (IOException ex) {
            Log.e(LOG_TAG, "General IO error: " + ex.getMessage(), ex);
        }
        finally {
            _xml.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LOG_TAG, "Upgrading database from version " + oldVersion + " to version " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);
    }
}

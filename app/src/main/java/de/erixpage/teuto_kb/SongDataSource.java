package de.erixpage.teuto_kb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 05.09.2016.
 */
public class SongDataSource {
    private static final String LOG_TAG = SongDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private SongDbHelper dbHelper;

    private String[] columns = {
            SongDbHelper.COLUMN_ID,
            SongDbHelper.COLUMN_NUMBER,
            SongDbHelper.COLUMN_PAGE,
            SongDbHelper.COLUMN_TITLE,
            SongDbHelper.COLUMN_BEGINNING,
            SongDbHelper.COLUMN_TONALITY,
            SongDbHelper.COLUMN_AUTHOR_MELODY,
            SongDbHelper.COLUMN_AUTHOR_TEXT,
            SongDbHelper.COLUMN_YEAR_MELODY,
            SongDbHelper.COLUMN_YEAR_TEXT,
            SongDbHelper.COLUMN_TEXT,
            SongDbHelper.COLUMN_NOTE
    };

    public SongDataSource(Context context) {
        Log.d(LOG_TAG, "DataSource is now creating the DbHelper.");
        dbHelper = new SongDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Requesting reference to database now.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Database reference acquired. Path to database: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Closed database using DbHelper.");
    }

    public Song createSong(int number, int page, String title, String beginning, String tonality,
                           String author_melody, String author_text, int year_melody, int year_text,
                           String text, String note) {
        ContentValues values = new ContentValues();
        values.put(SongDbHelper.COLUMN_NUMBER, number);
        values.put(SongDbHelper.COLUMN_PAGE, page);
        values.put(SongDbHelper.COLUMN_TITLE, title);
        values.put(SongDbHelper.COLUMN_BEGINNING, beginning);
        values.put(SongDbHelper.COLUMN_TONALITY, tonality);
        values.put(SongDbHelper.COLUMN_AUTHOR_MELODY, author_melody);
        values.put(SongDbHelper.COLUMN_AUTHOR_TEXT, author_text);
        values.put(SongDbHelper.COLUMN_YEAR_MELODY, year_melody);
        values.put(SongDbHelper.COLUMN_YEAR_TEXT, year_text);
        values.put(SongDbHelper.COLUMN_NOTE, note);
        values.put(SongDbHelper.COLUMN_TEXT, text);

        long insertId = database.insert(SongDbHelper.TABLE_SONGS, null, values);

        Cursor cursor = database.query(SongDbHelper.TABLE_SONGS, columns,
                SongDbHelper.COLUMN_ID + "=" + insertId, null, null, null, null);

        cursor.moveToFirst();
        Song song = cursorToSong(cursor);
        cursor.close();

        return song;
    }

    private Song cursorToSong(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(SongDbHelper.COLUMN_ID);
        int idNumber = cursor.getColumnIndex(SongDbHelper.COLUMN_NUMBER);
        int idPage = cursor.getColumnIndex(SongDbHelper.COLUMN_PAGE);
        int idTitle = cursor.getColumnIndex(SongDbHelper.COLUMN_TITLE);
        int idBeginning = cursor.getColumnIndex(SongDbHelper.COLUMN_BEGINNING);
        int idTonality = cursor.getColumnIndex(SongDbHelper.COLUMN_TONALITY);
        int idAuthorMelody = cursor.getColumnIndex(SongDbHelper.COLUMN_AUTHOR_MELODY);
        int idAuthorText = cursor.getColumnIndex(SongDbHelper.COLUMN_AUTHOR_TEXT);
        int idYearMelody = cursor.getColumnIndex(SongDbHelper.COLUMN_YEAR_MELODY);
        int idYearText = cursor.getColumnIndex(SongDbHelper.COLUMN_YEAR_TEXT);
        int idNote = cursor.getColumnIndex(SongDbHelper.COLUMN_NOTE);
        int idText = cursor.getColumnIndex(SongDbHelper.COLUMN_TEXT);

        long id = cursor.getLong(idIndex);
        int number = cursor.getInt(idNumber);
        int page = cursor.getInt(idPage);
        String title = cursor.getString(idTitle);
        String beginning = cursor.getString(idBeginning);
        String tonality = cursor.getString(idTonality);
        String author_melody = cursor.getString(idAuthorMelody);
        String author_text = cursor.getString(idAuthorText);
        int year_melody = cursor.getInt(idYearMelody);
        int year_text = cursor.getInt(idYearText);
        String note = cursor.getString(idNote);
        String text = cursor.getString(idText);

        Song song = new Song(id, number, page, title, beginning, text, tonality, author_melody,
                author_text, year_melody, year_text, note);

        return song;
    }

    public List<Song> getAllSongs() {
        List<Song> songList = new ArrayList<>();

        Cursor cursor = database.query(SongDbHelper.TABLE_SONGS, columns,
                null, null, null, null, null);

        cursor.moveToFirst();
        Song song;

        while (!cursor.isAfterLast()) {
            song = cursorToSong(cursor);
            songList.add(song);
            Log.d(LOG_TAG, "ID: " + song.getId() + ", Content: " + song.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return songList;
    }
}

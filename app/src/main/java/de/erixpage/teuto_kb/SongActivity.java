package de.erixpage.teuto_kb;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SongActivity extends AppCompatActivity {
    private static final String LOG_TAG = SongActivity.class.getSimpleName();
    private Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("songObject");
        Log.d(LOG_TAG, "Got song " + song.toString() + " from other activity");
        bar.setTitle(song.getTitle());
        TextView tv = (TextView) findViewById(R.id.text_view_song);
        tv.setText(song.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_song_info:
                Log.d(LOG_TAG, "Displaying song info");
                displaySongInfo();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySongInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(song.getDisplayDetails())
                .setTitle(R.string.action_song_info)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

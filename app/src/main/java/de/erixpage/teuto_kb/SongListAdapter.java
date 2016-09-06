package de.erixpage.teuto_kb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Eric on 05.09.2016.
 */
public class SongListAdapter extends ArrayAdapter<Song> {
    private static final String LOG_TAG = SongListAdapter.class.getSimpleName();

    private int layoutResourceId;

    public SongListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        layoutResourceId = textViewResourceId;
    }

    public SongListAdapter(Context context, int textViewResourceId, List<Song> items) {
        super(context, textViewResourceId, items);
        layoutResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            Song song = getItem(position);
            View v = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(layoutResourceId, null);
            } else {
                v = convertView;
            }

            TextView title = (TextView) v.findViewById(R.id.list_item_songs_textview_top);
            TextView info = (TextView) v.findViewById(R.id.list_item_songs_textview_bottom);

            title.setText(song.getDisplayText());
            info.setText(song.getDisplayInfo());

            return v;
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Error inflating list view: " + ex.getMessage());
            return null;
        }
    }
}

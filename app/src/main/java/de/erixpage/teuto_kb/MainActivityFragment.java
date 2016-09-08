package de.erixpage.teuto_kb;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Eric on 06.09.2016.
 */
public class MainActivityFragment extends Fragment {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        SongDataSource ds = new SongDataSource(rootView.getContext());
        ds.open();
        List<Song> songs = ds.getAllSongs();
        ds.close();
        SongListAdapter adapter = new SongListAdapter(rootView.getContext(), R.layout.list_item_songs, songs);

        final ListView songListView = (ListView) rootView.findViewById(R.id.listview_songs);
        songListView.setAdapter(adapter);

        songListView.setClickable(true);
        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long arg3) {
                Song s = (Song) songListView.getItemAtPosition(position);
                Intent i = new Intent(rootView.getContext(), SongActivity.class);
                Log.d(LOG_TAG, "Passing object to new intent: " + s.toString());
                i.putExtra("songObject", s);
                getActivity().startActivity(i);
            }
        });

        return rootView;
    }
}

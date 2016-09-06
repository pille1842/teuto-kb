package de.erixpage.teuto_kb;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Eric on 06.09.2016.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        SongDataSource ds = new SongDataSource(rootView.getContext());
        ds.open();
        List<Song> songs = ds.getAllSongs();
        ds.close();
        SongListAdapter adapter = new SongListAdapter(rootView.getContext(), R.layout.list_item_songs, songs);

        ListView songListView = (ListView) rootView.findViewById(R.id.listview_songs);
        songListView.setAdapter(adapter);

        return rootView;
    }
}

package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bunniesarecute.admin.textmadness.DictionaryAPI.DictionaryInterface;

/**
 * Created by katecatlin on 10/21/14.
 */

public class WordSelectFragment extends Fragment implements DictionaryInterface {
    POSAdapter mAdapter;

    @Override
    public String getWordFound(String wordFound) {
        return null;
    }

    public WordSelectFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAdapter = new POSAdapter(getActivity(), WordSelect.posObjectArrayList);
    }

    @Override
    public void onStart () {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_word_select, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowID) {
                POSObject thisObject = mAdapter.getItem(position);

                Intent intent = new Intent();

                intent.putExtra("RANDOM_WORD", "*****");

                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }

        });


        return rootView;
    }


}
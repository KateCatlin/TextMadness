package com.bunniesarecute.admin.textmadness;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.content.Context;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by katecatlin on 10/21/14.
 */

public class WordSelectFragment extends Fragment {

    public WordSelectFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        String[] partsOfSpeech = new String[] { "Adjective", "Noun", "Verb", "Adverb" };

        final ArrayList<String> arrayListOfPartsOfSpeech = new ArrayList<String>();
        for (int i = 0; i< partsOfSpeech.length; i++) {
            arrayListOfPartsOfSpeech.add(partsOfSpeech[i]);
        }

        final StableArrayAdapter WordAdapter = new StableArrayAdapter (getActivity(),
                android.R.layout.simple_list_item_1, arrayListOfPartsOfSpeech);
        listView.setAdapter(WordAdapter);

        partsOfSpeech.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String partOfSpeech = WordAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), MainActivity.class);
//                   Add extra here
            }

        });



        return rootView;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }



}
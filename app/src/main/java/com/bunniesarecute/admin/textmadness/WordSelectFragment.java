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

        final ArrayList<String> arrayListOfPartsOfSpeech = new ArrayList<String>();
        for (int i = 0; i< WordSelect.partsOfSpeech.length; i++) {
            arrayListOfPartsOfSpeech.add(WordSelect.partsOfSpeech[i]);
        }

        return rootView;
    }


}
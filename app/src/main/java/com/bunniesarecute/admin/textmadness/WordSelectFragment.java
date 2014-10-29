package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bunniesarecute.admin.textmadness.DictionaryAPI.DictionaryInterface;

import java.util.Random;

/**
 * Created by katecatlin on 10/21/14.
 */

public class WordSelectFragment extends Fragment implements DictionaryInterface {
    POSAdapter mAdapter;
    Random randomWordSelector;
    String posSelected;
    private boolean randomTrue;
    private DictionaryAPI anApi = new DictionaryAPI();

    private static final String LOG_TAG = "WordSelectFragment";



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

        //DictionaryAPI anApi = new DictionaryAPI();

        anApi.setDictionaryInterface(this);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowID) {
                POSObject thisObject = mAdapter.getItem(position);

                posSelected = thisObject.getpOS().toLowerCase();
                anApi.execute(posSelected);


            }

        });


        return rootView;
    }

    @Override
    public void foundAWord(String word){

        Log.d(LOG_TAG, word);


        TextBuilder.addRandomWordToMap(word);

        Intent intent = new Intent();

        intent.putExtra("RANDOM_WORD", posSelected);

        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }


}
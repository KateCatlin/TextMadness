package com.bunniesarecute.admin.textmadness;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SwapRandomWord extends Fragment {

    private RandomizerDictionaryAPI mRandomizerDictionaryAPI;


    public SwapRandomWord() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRandomizerDictionaryAPI = new RandomizerDictionaryAPI();
        String wordToSwap = getActivity().getIntent().getStringExtra("RAND_FROM_MESSAGE");
        mRandomizerDictionaryAPI.execute(wordToSwap);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_swap_random_word, container, false);

        return rootView;
    }




}

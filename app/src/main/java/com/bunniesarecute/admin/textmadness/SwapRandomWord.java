package com.bunniesarecute.admin.textmadness;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bunniesarecute.admin.textmadness.DictionaryAPI.DictionaryInterface;

public class SwapRandomWord extends Fragment implements DictionaryInterface{

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


    @Override
    public void foundAWord(String wordFound) {
        TextBuilder.replaceSwappedWordWithRandom(wordFound);
    }
}

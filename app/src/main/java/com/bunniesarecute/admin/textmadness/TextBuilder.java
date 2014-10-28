package com.bunniesarecute.admin.textmadness;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 10/21/14.
 */
public class TextBuilder {
    private ArrayList<String> editTextStrings = new ArrayList<String>();
    private String mTextFromMainEditText;
    private HashMap<Integer, String> wordMap = new HashMap<Integer, String>();
    private Integer wordCounter = 0;


    public Integer getWordCounter(){
        return wordCounter;
    }

    public void wordCountUp(){
        wordCounter = getWordCounter() + 1;
    }

    public ArrayList<String> getEditTextStrings(){
        return editTextStrings;
    }

    public String getTextFromMainEditText() {
        Log.i("textEdit class", mTextFromMainEditText);
        return mTextFromMainEditText;
    }

    public void addTextToStringArrayList(String text) {
        int i;
        String[] splitText = text.split(" ");
        for (i = 0; i < splitText.length; i++) {
            getEditTextStrings().add(splitText[i]);
        }
        Log.i("editText String", getEditTextStrings().toString());
    }

    public void addRandomWordToArrayList(String word) {
        getEditTextStrings().add(word);
    }

    public void buildText() {
        StringBuilder mStringBuilder = new StringBuilder();
        for(String current: editTextStrings) {
            mStringBuilder.append(current);
            mStringBuilder.append(" ");
        }
        mTextFromMainEditText = mStringBuilder.toString();
    }

    public void addRandomWordToMap(String word){
        wordMap.put(getWordCounter(), word);
        Log.i("word added to map", wordMap.get(getWordCounter()));
        wordCountUp();
    }

    public String getRandomWordFromMap(Integer wordCounter){
        return wordMap.get(wordCounter);
    }
}

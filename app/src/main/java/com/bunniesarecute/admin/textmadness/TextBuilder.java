package com.bunniesarecute.admin.textmadness;

import android.util.Log;


import java.sql.Array;
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
    WordSelect mWordSelect = new WordSelect();



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

    public HashMap getWordMap(){
        return wordMap;
    }

    public Object getListOfKeys(){
        return getWordMap().keySet().toArray();
    }

    public void addRandomWordToMap(String word){
        wordMap.put(getWordCounter(), word);
        Log.i("word added to map", wordMap.get(getWordCounter()));
        wordCountUp();
    }

    public String getRandomWordFromMap(Integer wordCounter){
        return wordMap.get(wordCounter);
    }

    public String swapOutMaskedWord(String messageFromMain){
        addTextToStringArrayList(messageFromMain);
        String unMaskedMessage = "";
        for(int i = 0; i < editTextStrings.size(); i++)
        {
            String wordToCheck = getEditTextStrings().get(i);
            if(wordIsMasked(wordToCheck)){

            }
        }
    }

    public boolean wordIsMasked(String wordToCompare){
        for(int i = 0; i < mWordSelect.posObjectArrayList.size(); i++){
            String posWord = mWordSelect.posObjectArrayList.get(i).getpOS();
            if(posWord.equals(wordToCompare)){
                return true;
            }
        }
        return false;
    }

}

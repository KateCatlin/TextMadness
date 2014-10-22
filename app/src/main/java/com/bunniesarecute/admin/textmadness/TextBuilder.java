package com.bunniesarecute.admin.textmadness;

import java.util.ArrayList;

/**
 * Created by admin on 10/21/14.
 */
public class TextBuilder {
    private ArrayList<String> editTextStrings = new ArrayList<String>();
    private String mTextFromMainEditText;

    public String getTextFromMainEditText() {
        return mTextFromMainEditText;
    }

    public void addTextToStringArrayList(String text) {
        int i;
        String[] splitText = text.split(" ");
        for (i = 0; i < splitText.length; i++) {
            editTextStrings.add(splitText[i]);
        }
    }

    public void addRandomWordToArrayList(String word) {
        editTextStrings.add(word);
    }

    public void buildText() {
        StringBuilder mStringBuilder = new StringBuilder();
        for(String current: editTextStrings) {
            mStringBuilder.append(current);
        }
        mTextFromMainEditText = mStringBuilder.toString();
    }
}

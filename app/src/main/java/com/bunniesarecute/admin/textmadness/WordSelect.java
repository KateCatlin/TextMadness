package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import java.util.ArrayList;


public class WordSelect extends Activity {

    public static ArrayList<POSObject> posObjectArrayList = new ArrayList<POSObject>();

    //    final public static String[] partsOfSpeech = new String[] { "Adjective", "Noun", "Verb", "Adverb" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_select);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new WordSelectFragment())
                    .commit();
        }

        POSObject adj = new POSObject("Adjective","Example: Spicy, Painful, Thundering, Magenta, Jumbo, Blobby...");
        POSObject noun = new POSObject("Noun", "Example: Advice, Congregation, Forget-me-not, Cookie, Evolution...");
        POSObject verb = new POSObject("Verb", "Example: Add, Bake, Scatter, Zoom, Imagine, Fix...");
        POSObject adverb = new POSObject("Adverb", "Example: Quickly, Delightfully, Sloppily, Sorrowfully, Firmly...");

        posObjectArrayList.add(adj);
        posObjectArrayList.add(noun);
        posObjectArrayList.add(verb);
        posObjectArrayList.add(adverb);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.word_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

}

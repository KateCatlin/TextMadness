package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends Activity {

    Button generateWordButton;
    Button shareTextButton;
    EditText mainEditText;
    ArrayList<String> editTextStrings = new ArrayList<String>();

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
//String builder
    public String buildText() {
        String completedTextMessage = "";
        for(String current: editTextStrings) {
            completedTextMessage = (completedTextMessage + " " + current);
        }
        return completedTextMessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainEditText = (EditText) findViewById(R.id.edit_text);
        generateWordButton = (Button) findViewById(R.id.generate_word_button);
        generateWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTextToStringArrayList(mainEditText.getText().toString());
            }
        });
        shareTextButton = (Button) findViewById(R.id.send);
        shareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}

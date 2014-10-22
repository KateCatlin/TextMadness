package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


public class MainActivity extends Activity {

    Button insertWordButton;
    Button shareTextButton;
    EditText mainEditText;
    TextBuilder mTextBuilder = new TextBuilder();
    String mFullTextMessage = "";

    static final int GENERATE_RANDOM_WORD_REQUEST = 41;
    static final String FULL_TEXT = "com.bunniesarecute.admin.textmadness.mainactivity.mFullTextMessage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainEditText = (EditText) findViewById(R.id.edit_text);
        insertWordButton = (Button) findViewById(R.id.generate_word_button);
        insertWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextBuilder.addTextToStringArrayList(mainEditText.getText().toString());
                Intent genWordIntent = new Intent(mainEditText.getContext(), WordSelect.class);
                startActivityForResult(genWordIntent, GENERATE_RANDOM_WORD_REQUEST);

                //once logic is set for getting random word, set it with mTextBuilder.addRandomWordToArrayList(*whatever the new random word is*)
            }
        });
        shareTextButton = (Button) findViewById(R.id.send);
        shareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextBuilder.buildText();
                mFullTextMessage = mTextBuilder.getTextFromMainEditText();
                Intent sendMessageIntent = new Intent(); //new share message activity
                sendMessageIntent.putExtra(FULL_TEXT, mFullTextMessage);
                startActivity(sendMessageIntent);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == GENERATE_RANDOM_WORD_REQUEST) {
//            if (requestCode == RESULT_OK) {
//                // use random word here
//            }
//        }
//    }
}

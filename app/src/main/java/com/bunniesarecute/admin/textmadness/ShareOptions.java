package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class ShareOptions extends Activity implements OnClickListener {

    private ImageButton mTextMessageButton;
    private ImageButton mEmailMessageButton;
    private ImageButton mFacebookButton;
    private ImageButton mTwitterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_options);
        mTextMessageButton = (ImageButton) findViewById(R.id.text_message_button);
        mTextMessageButton.setOnClickListener(this);
        mEmailMessageButton = (ImageButton) findViewById(R.id.email_button);
        mEmailMessageButton.setOnClickListener(this);
        mFacebookButton = (ImageButton) findViewById(R.id.facebook_button);
        mFacebookButton.setOnClickListener(this);
        mTwitterButton = (ImageButton) findViewById(R.id.twitter_button);
        mTwitterButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_options, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.text_message_button:

            case R.id.email_button:

            case R.id.facebook_button:

            case R.id.twitter_button:
        }

    }
}

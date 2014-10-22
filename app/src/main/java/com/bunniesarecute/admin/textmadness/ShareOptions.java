package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new EmailMessageNextStepFragment())
                        .commit();

            case R.id.email_button:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new EmailMessageNextStepFragment())
                        .commit();

            case R.id.facebook_button:

            case R.id.twitter_button:
        }

    }

    public static class TextMessageNextStepFragment extends Fragment {

        Button doneButton;
        EditText whereToSend;
        String numberOrEmail;

        public TextMessageNextStepFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_share_textmessage, container, false);
            numberOrEmail = "";

            doneButton = (Button) rootView.findViewById(R.id.done_button);
            whereToSend = (EditText) rootView.findViewById(R.id.info_enter_space);

            doneButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberOrEmail = whereToSend.getText().toString();
                }
            });

            return rootView;
        }
    }

    public static class EmailMessageNextStepFragment extends Fragment {

        Button doneButton;
        EditText whereToSend;
        String numberOrEmail;

        public EmailMessageNextStepFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_share_textmessage, container, false);
            numberOrEmail = "";

            doneButton = (Button) rootView.findViewById(R.id.done_button);
            whereToSend = (EditText) rootView.findViewById(R.id.info_enter_space);

            doneButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberOrEmail = whereToSend.getText().toString();
                }
            });

            return rootView;
        }
    }
}

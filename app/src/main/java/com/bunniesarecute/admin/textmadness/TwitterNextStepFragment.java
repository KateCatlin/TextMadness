package com.bunniesarecute.admin.textmadness;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by katecatlin on 10/29/14.
 */
public class TwitterNextStepFragment extends Fragment {
    private Button doneButton;
    private EditText whereToSend;
    private String twitterHandle;
    private String messageToSend;
    private String modifiedMessageToSend;
    public final String consumer_key = "NrmcH7LxzQB0BhPcUtkHNw4F5";
    public final String secret_key = "6y7cJbniJUkGt3huYnfIx4GVnLEfArJBHl4XWnbKyWoo1jGhGF";
    public final String access_token = "1131823837-ShUn1OBUE179rxnZgICRQ2LkUQSQFjjP7miOvwT";
    public final String access_token_secret = "Ojka25GTJeAVYJb6hZdwWz1NMevkIPR9Jj7ef7gtdLmJF";
    ContinueOrNot cont = new ContinueOrNot();

    public TwitterNextStepFragment () {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_share_twittermessage, container, false);
        twitterHandle = "";
        messageToSend = getActivity().getIntent().getStringExtra(MainActivity.FULL_TEXT);

        doneButton = (Button) rootView.findViewById(R.id.done_button);
        whereToSend = (EditText) rootView.findViewById(R.id.info_enter_space);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twitterHandle = whereToSend.getText().toString();
                modifiedMessageToSend = twitterHandle + " - " + messageToSend;
                sendTweet(modifiedMessageToSend);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new ContinueOrNot())

                        .commit();

            }
        });


        return rootView;
    }

    private void sendTweet(String message){
            if (isNetworkAvailable()) {
                Twitt_Sharing twitt = new Twitt_Sharing(MainActivity.this,
                        consumer_key, secret_key);
                string_img_url = "http://3.bp.blogspot.com/_Y8u09A7q7DU/S-o0pf4EqwI/AAAAAAAAFHI/PdRKv8iaq70/s1600/id-do-anything-logo.jpg";
                string_msg = "http://chintankhetiya.wordpress.com/";
                // here we have web url image so we have to make it as file to
                // upload
                String_to_File(string_img_url);
                // Now share both message & image to sharing activity
                twitt.shareToTwitter(string_msg, casted_image);

            } else {
                showToast("No Network Connection Available !!!");
            }
        }
    }

    Intent share = new Intent(Intent.ACTION_SEND);
    share.setType("text/plain");
    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

    share.putExtra(Intent.EXTRA_TEXT, message);

    startActivity(Intent.createChooser(share, ));
}

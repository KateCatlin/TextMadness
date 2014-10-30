package com.bunniesarecute.admin.textmadness;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

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

        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, message);
        tweetIntent.setType("text/plain");

        PackageManager packManager = getActivity().getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for(ResolveInfo resolveInfo: resolvedInfoList){
            if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name );
                resolved = true;
                break;
            }
        }
        if(resolved){
            startActivity(tweetIntent);
        }else{
            Toast.makeText(getActivity(), "Twitter app isn't found", Toast.LENGTH_LONG).show();
        }

//        String url = "http://www.twitter.com/intent/tweet?url=YOURURL&text=" + message;
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);

        }
    }



//
//    Intent share = new Intent(Intent.ACTION_SEND);
//    share.setType("text/plain");
//    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//
//    share.putExtra(Intent.EXTRA_TEXT, message);
//
//    startActivity(Intent.createChooser(share, ));


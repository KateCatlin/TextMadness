package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.content.Context;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by katecatlin on 10/21/14.
 */

public class WordSelectFragment extends Fragment {
    private final String LOG_TAG = WordSelectFragment.class.getSimpleName();
    POSAdapter mAdapter;

    public WordSelectFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAdapter = new POSAdapter(getActivity(), WordSelect.posObjectArrayList);
    }

    @Override
    public void onStart () {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_word_select, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowID) {
                POSObject thisObject = mAdapter.getItem(position);
                DictionaryAPI dictionary = new DictionaryAPI();
                Intent intent = new Intent();
                String posString = mAdapter.getItem(position).getpOS();

                intent.putExtra("RANDOM_WORD", "*****");
                Log.d(LOG_TAG, "The POS chosen is " + posString);

                DictionaryAPI dictionaryAPI = new DictionaryAPI();
                dictionaryAPI.execute(posString);

                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }

        });


        return rootView;
    }


}
package com.bunniesarecute.admin.textmadness;

/**
 * Created by katecatlin on 10/21/14.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class POSAdapter extends ArrayAdapter<POSObject>{
    private final Context mContext;

    ArrayList<POSObject> thisArrayList;


    public POSAdapter(Context context, ArrayList<POSObject> thisArrayList) {
        super(context, R.layout.row_part_of_speech, thisArrayList);
        this.mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisRow = inflater.inflate(R.layout.row_part_of_speech, parent, false);
        POSObject thisObject = getItem(position);

        thisArrayList = WordSelect.posObjectArrayList;

        TextView mainText = (TextView) thisRow.findViewById(R.id.part_of_speech_textview);
        mainText.setText(thisObject.getpOS());

        TextView exampleText = (TextView) thisRow.findViewById(R.id.example_pos_textview);
        exampleText.setText(thisObject.getpOSExample());

        return thisRow;

    }


}
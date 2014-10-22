package com.bunniesarecute.admin.textmadness;

/**
 * Created by katecatlin on 10/21/14.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.lang.reflect.Array;

public class POSAdapter extends BaseAdapter{
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";
    String[] partsOfSpeech = WordSelect.partsOfSpeech;

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public POSAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        return partsOfSpeech.length;
    }

    @Override
    public Object getItem(int position) {
        return partsOfSpeech[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.row_part_of_speech, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.select_button);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.part_of_speech_textview);
            holder.authorTextView = (TextView) convertView.findViewById(R.id.example_pos_textview);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }
        // More code after this
        // Grab the title and author from the JSON
        String pOS = "";
        String pOSExample = "";

        if (jsonObject.has("title")) {
            bookTitle = jsonObject.optString("title");
        }

        if (jsonObject.has("author_name")) {
            authorName = jsonObject.optJSONArray("author_name").optString(0);
        }

        // Send these Strings to the TextViews for display
        holder.titleTextView.setText(bookTitle);
        holder.authorTextView.setText(authorName);


        return convertView;
    }

    private static class ViewHolder {
        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public TextView authorTextView;
    }
}
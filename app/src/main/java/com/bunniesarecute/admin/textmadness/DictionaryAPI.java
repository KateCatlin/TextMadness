package com.bunniesarecute.admin.textmadness;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by admin on 10/22/14.
 */
public class DictionaryAPI extends AsyncTask<String, Void, String> {
    private final String LOG_TAG = DictionaryAPI.class.getSimpleName();
    String API_KEY = "6aa015c0d84b01a6c205f6848a6dea42bcb91d757d4341dde";
    String searchURLString = "";

    public String pickDirtyWord() {
        String badWord = "";
        String[] arrayOfBadWords = {"ass", "sexual", "anal", "penis", "vagina"};
        Random random = new Random();
        int i = random.nextInt(arrayOfBadWords.length);
        Log.d(LOG_TAG, "The int i is " + i);
        return badWord = (arrayOfBadWords[i]);
    }

    protected String doInBackground(String... params) {
        String rawJSONInfo;
        String badWord = pickDirtyWord();
        Log.d(LOG_TAG, "The bad word is " + badWord);

        if (params.length == 0) {
            Log.d(LOG_TAG, "params.length was null");
            return null;

        }

        if (badWord.length() == 0) {
            Log.d(LOG_TAG, "badWord.length is null");
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
//      ^^Wraps an existing Reader and buffers the input. Expensive interaction with the underlying reader is minimized, since most (smaller) requests can be satisfied by accessing the buffer alone.


        try {

            Log.d(LOG_TAG, "The param is " + params[0]);
            String urlPartOne = "http://api.wordnik.com:80/v4/words.json/reverseDictionary?query=";
            String urlPartTwo = "&includePartOfSpeech=";
            String urlPartThree = "&maxCorpusCount=1&minLength=1&includeTags=false&limit=10&api_key=";
            searchURLString = urlPartOne + badWord + urlPartTwo + params[0] + urlPartThree + API_KEY;


            URL searchURL = new URL(searchURLString.toString());
            Log.d(LOG_TAG, "The URL is " + searchURL );

            urlConnection = (HttpURLConnection) searchURL.openConnection();
            urlConnection.setRequestMethod("GET"); //Sets the request command which will be sent to the remote HTTP server.
            urlConnection.connect(); //Opens a connection to the resource.

//          Input Stream: A readable source of bytes. Most clients will use input streams that read data from the file system (FileInputStream), the network (getInputStream()/getInputStream()), or from an in-memory byte array (ByteArrayInputStream).
//          InputStreamReader: A class for turning a byte stream into a character stream.
//          StringBuffer: A modifiable sequence of characters for use in creating strings, where all accesses are synchronized. This class has mostly been replaced by StringBuilder because this synchronization is rarely useful. This class is mainly used to interact with legacy APIs that expose it.
            InputStream inputStream = urlConnection.getInputStream(); //The response body may be read from the stream returned by getInputStream(). If the response has no body, that method returns an empty stream.
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Log.d(LOG_TAG, "inputSteam ==null");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) !=null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                Log.d(LOG_TAG, "buffer.length ==0");
                return null;
            }

            rawJSONInfo = buffer.toString();

            Log.v(LOG_TAG, "JSON string: " + rawJSONInfo);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the JSON data, there's no point in attemping
            // to parse it.
            Log.d(LOG_TAG, "IOException after trying to send JSON to buffer");
            return null;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return wordSearch(rawJSONInfo);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);

            e.printStackTrace();
        }
        return null;
        }


    public String wordSearch(String rawJSONInfo) throws JSONException {
        final String JSON_RESULT = "results";
        final String WORD_RETURN = "word";
        Log.d(LOG_TAG, "rawJSONInfo in wordSearch is " + rawJSONInfo);

        JSONObject wordJSON = new JSONObject(rawJSONInfo);
        JSONArray wordJSONArray = wordJSON.getJSONArray(JSON_RESULT);

        int random = new Random().nextInt(wordJSONArray.length());

        JSONObject jsonWord = wordJSONArray.getJSONObject(random);

        String finalWord = jsonWord.getString(WORD_RETURN);

        return finalWord;
        }

    protected void onPreExecute() {
        //DOES THIS NEED ANYTHING?
    }


//    @Override


//    @Override
    protected void onPostExecute(String result) {
       Log.d(LOG_TAG, "The word returned is..." + result);
    }
}

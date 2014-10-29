package com.bunniesarecute.admin.textmadness;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 10/22/14.
 */
public class RandomizerDictionaryAPI extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = "RandomDictionaryAPI";

    public RandomDictionaryInterface mRandomDictionaryInterface;

    String randomDictionaryWordsStr = null;
    @Override
    protected String doInBackground(String... strings) {


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;


        String wordToDefine = strings[0];
        final String URI_BASE = "http://api.wordnik.com/v4/words.json/";
        final String URI_END = "/definitions?limit=1&includeRelated=false&useCanonical=false&includeTags=false&api_key=";
        final String API_KEY = "6aa015c0d84b01a6c205f6848a6dea42bcb91d757d4341dde";
        URL urlToUse = null;

        //http://api.wordnik.com:80/v4/word.json/shoe/definitions?limit=1&includeRelated=false&useCanonical=false&includeTags=false&api_key=
        try {
            String urlString = URI_BASE + wordToDefine + URI_END + API_KEY;


            urlToUse = new URL(urlString);

            Log.i("url", urlString);


            urlConnection = (HttpURLConnection) urlToUse.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if(inStream == null)
            {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            randomDictionaryWordsStr = buffer.toString();

            Log.d(LOG_TAG, randomDictionaryWordsStr);
            //final String TOTAL_RESULTS = "totalResults";

        }
        catch (MalformedURLException e){
                Log.e("createUrl", e.getMessage());}

        catch (IOException e){
            Log.e("connection", e.getMessage());
        }
        finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        try{
            return getWordFromJSON(randomDictionaryWordsStr);
        }catch (JSONException e){
            Log.e("JSON error", e.getMessage());
        }
        return null;
    }

    private String getWordFromJSON(String wordListString) throws JSONException
    {
        final String PART_OF_SPEECH = "partOfSpeech";

        //Make a new JSON Object from JSONString
        JSONObject jsonObject = new JSONObject(wordListString);

        Log.i("JsonArray", jsonObject.toString());

            String aWordFound;

            aWordFound = jsonObject.getString(PART_OF_SPEECH);

            Log.i("wordsFound", aWordFound.toString());

        return aWordFound;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mRandomDictionaryInterface.randomWord(true);
        DictionaryAPI dictionaryAPI = new DictionaryAPI();
        dictionaryAPI.execute(s);


    }



    public void setRandomDictionaryInterface(RandomDictionaryInterface something){
        this.mRandomDictionaryInterface = something;
    }

    public interface RandomDictionaryInterface{
       public boolean randomWord(Boolean randomTrue);
    }

}


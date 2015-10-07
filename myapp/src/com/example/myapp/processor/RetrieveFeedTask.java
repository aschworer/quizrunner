package com.example.myapp.processor;

import android.os.AsyncTask;
import android.util.Log;
import com.example.myapp.AsyncResponse;
import com.example.myapp.model.Question;
import com.google.gson.Gson;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by aschworer on 30/08/2014.
 */
public class RetrieveFeedTask extends AsyncTask<String, Void, Question> {

    public AsyncResponse delegate = null;

    protected Question doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            InputStream in = url.openStream();
            try {
                final InputStreamReader reader = new InputStreamReader(in);
                final Gson gson = new Gson();
                final Question q = gson.fromJson(reader, Question.class);
                in.close();
                return q;
            } catch (Exception e) {
                Log.e("exception", e.getMessage());
            } finally {
                in.close();
            }
        } catch (Exception e) {
            Log.e("exception", e.getMessage());
        }
        return null;
    }


    protected Question doInBackgroundXML(String... urls) {
        try {
//
            URL url = new URL(urls[0]);
            InputStream in = url.openStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            String result, line = reader.readLine();
//            result = line;
//            while((line = reader.readLine()) != null) {
//                result += line;
//            }
//            showQuestion(new Persister().read(Question.class, this.getResources().openRawResource(R.raw.q)));
            try {

                Question q = new Persister().read(Question.class, in);
                in.close();
                return q;
            } catch (Exception e) {
                Log.e("exception", e.getMessage());
            } finally {
                in.close();
            }
        } catch (Exception e) {
            Log.e("exception", e.getMessage());
        }
        return null;
    }

    protected void onPostExecute(Question feed) {
        // TODO: check this.exception
        // TODO: do something with the feed

        delegate.processFinish(feed);
//        showQuestion(new RetrieveFeedTask().execute(new String[]{url}).get);
    }

    public RetrieveFeedTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }
}
package com.example.myapp.processor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Created by c38854 on 30/08/2014.
 */
public class RetrieveImgTask extends AsyncTask<String, Void, Bitmap> {

    public AsyncResponse delegate=null;

    protected Bitmap doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            InputStream in = url.openConnection().getInputStream();
            try {
                final Bitmap q = BitmapFactory.decodeStream(in);
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


    protected void onPostExecute(Bitmap feed) {
        delegate.processFinish(feed);
    }

    public RetrieveImgTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }
}
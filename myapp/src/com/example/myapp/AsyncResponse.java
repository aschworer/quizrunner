package com.example.myapp;

import android.graphics.Bitmap;
import com.example.myapp.model.Question;

/**
 * Created by c38854 on 30/08/2014.
 */
public interface AsyncResponse {
    void processFinish(Question output);
    void processFinish(Bitmap output);
}

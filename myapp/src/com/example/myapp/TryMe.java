package com.example.myapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myapp.model.Answer;
import com.example.myapp.model.Question;
import com.example.myapp.processor.RetrieveFeedTask;
import com.example.myapp.processor.RetrieveImgTask;

/**
 * Trial version
 */
public class TryMe extends Activity implements AsyncResponse {

    boolean finishedImage;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
//        asyncTask.delegate = this;
//        findViewById(R.id.pic).setVisibility(View.INVISIBLE);

        setContentView(R.layout.main);
        ((ImageView) findViewById(R.id.pic)).setImageResource(0);
        try {
            String url = "http://feed-xml.googlecode.com/svn/trunk/q.json";
            RetrieveFeedTask retrieveFeedTask = new RetrieveFeedTask(this);
            retrieveFeedTask.execute(url);
        } catch (Exception e) {
            Log.e("exception", e.getMessage());
        }
    }


    private void showQuestion(Question question) {
        final TextView text = (TextView) findViewById(R.id.text);


        if (question.getText() != null) text.setText(text.getText() + "\n\n" + question.getText());
        if (question.getImageURL() != null) {
            try {
                finishedImage = false;
                RetrieveImgTask task = new RetrieveImgTask(this);
                task.execute(question.getImageURL());
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }
        }

        for (Answer answer : question.getAnswers()) {
            appendButton(answer);
        }
    }

    private void appendButton(final Answer currentAnswer) {

        final Button btn = new Button(this);
        btn.setText(currentAnswer.getButton());
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("info", "button clicked");
                final TextView text = (TextView) findViewById(R.id.text);
                text.setText(currentAnswer.getText());
                removeAllButtons();
                if (currentAnswer.getQuestion() != null) {
                    showQuestion(currentAnswer.getQuestion());
                } else {
                    showExitButton();
                }
            }
        });

        showButton(btn);
    }

    private void removeAllButtons() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.buttons);
        layout.removeAllViews();
    }

    private void showButton(Button btn) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.buttons);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layout.addView(btn, lp);

    }

    private void showExitButton() {
        Button btnExit = new Button(this);
        btnExit.setText("CLOSE");
        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        showButton(btnExit);
    }


    @Override
    public void processFinish(Question output) {
        showQuestion(output);
    }

    @Override
    public void processFinish(Bitmap output) {
        final ImageView imageView = (ImageView) findViewById(R.id.pic);
        imageView.setImageBitmap(output);
//        imageView.setVisibility(View.VISIBLE);
        finishedImage = true;

    }
}

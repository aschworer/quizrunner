package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by aschworer on 29/08/2014.
 */
public class Question implements Serializable {

    @SerializedName("text")
    private String text;

    @SerializedName("imageURL")
    private String imageURL;

    @SerializedName("answers")
    private Set<Answer> answers;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public String getImageURL() {
        return imageURL;
    }
}

package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aschworer on 29/08/2014.
 */
public class Answer implements Serializable {

    @SerializedName("button")
    private String button;

    @SerializedName("text")
    private String text;

    @SerializedName("question")
    private Question question;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}

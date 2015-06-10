package com.example.myapp.model;

import com.google.gson.annotations.SerializedName;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by c38854 on 29/08/2014.
 */
//@Root(strict = false)
public class Question implements Serializable {

//    @Element

    @SerializedName( "text" )
    private String text;
//    @ElementList(inline = true)

    @SerializedName( "imageURL" )
    private String imageURL;

    @SerializedName( "answers" )
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

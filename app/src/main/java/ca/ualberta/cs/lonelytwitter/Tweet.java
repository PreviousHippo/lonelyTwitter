package ca.ualberta.cs.lonelytwitter;

import android.text.BoringLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by thu2 on 9/15/15.
 */
public abstract class Tweet extends Object implements Tweetable{
    private String text;
    private Date date;
    private ArrayList<CurrentMood> moodList;

    public String getText() {
        return text;
    }

    public void setText(String text) {
            if (text.length() <= 140) {
                this.text = text;
            } else {
                throw new IllegalArgumentException("Tweets cannot be tha long!, shake");
            }

    }


    public Date getDate() {
        return date;

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date();
    }

    public Tweet(String tweet, Date date) {
        text = tweet;
        this.date = date;
        /**
         * or you could use:
         * public Tweet(String text, Date date) {
         this.text = text; //"this" is the pointer to the current object
         this.date = date;
         */
    }
    public abstract Boolean isImportant();
}

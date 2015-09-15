package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by thu2 on 9/15/15.
 */
public abstract class CurrentMood {
    private String Mood;
    private Date date;


    public String getText() {
        return Mood;
    }

    public void setText(String Mood) {
        if (Mood.length() <= 140) {
            this.Mood = Mood;
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

    public CurrentMood(String Mood) {
        this.Mood = Mood;
        this.date = new Date();
    }

    public CurrentMood(String mood, Date date) {
        this.Mood = mood;
        this.date = date;
        /**
         * or you could use:
         * public Tweet(String text, Date date) {
         this.text = text; //"this" is the pointer to the current object
         this.date = date;
         */
    }
    public abstract String isHappy();
}

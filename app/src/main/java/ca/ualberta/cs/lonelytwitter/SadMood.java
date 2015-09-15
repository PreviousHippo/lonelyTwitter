package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by thu2 on 9/15/15.
 */
public class SadMood extends CurrentMood{
    public SadMood(String mood, Date date) {
        super(mood, date);
    }

    public SadMood(String Mood) {
        super(Mood);
    }

    public String isHappy() {
        return "sad";
    }
}

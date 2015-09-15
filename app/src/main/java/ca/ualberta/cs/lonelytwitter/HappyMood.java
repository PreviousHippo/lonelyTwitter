package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
/**
 * Created by thu2 on 9/15/15.
 */
public class HappyMood extends CurrentMood{
    public HappyMood(String Mood) {
        super(Mood);// "super" called back the text in tweet
    }

    public HappyMood(String mood, Date date) {
        super(mood, date);
    }

    public String isHappy(){
        return "happy";
    };

    @Override
    public String getText(){
        return "happy: " + super.getText();
    }
}

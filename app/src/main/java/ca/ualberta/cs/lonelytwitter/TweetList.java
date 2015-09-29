package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by thu2 on 9/29/15.
 */
public class TweetList {
    private  Tweet mostRecentTweet;
    private  Tweet needRemoveTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet){
        mostRecentTweet = tweet;
        tweets.add(tweet);

    }
    public Tweet getMostRecentTweet(){
        return mostRecentTweet;
    }

    public int count(){
        return tweets.size();
    }
    public ArrayList getTweet(){
        return tweets;
    }
    public boolean HasTweet(Tweet tweet) {
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).equals(tweet)) {
                return true;
            }
        }
        return false;
    }

    public void delete(Tweet tweet){
        needRemoveTweet = tweet;
        tweets.remove(tweet);
    }

}

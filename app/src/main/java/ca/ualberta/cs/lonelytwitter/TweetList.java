package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;

import java.util.ArrayList;
import ca.ualberta.cs.lonelytwitter.MyObserver;
/**
 * Created by thu2 on 9/29/15.
 */
//want to make observable
public class TweetList {
    private  Tweet mostRecentTweet;
    //private  Tweet needRemoveTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet){
        mostRecentTweet = tweet;
        tweets.add(tweet);
        tweet.addObserver(this);
        notifyAllObservers();

    }
    public Tweet getMostRecentTweet(){
        return mostRecentTweet;
    }

    public int count(){
        return tweets.size();
    }

    public void addObserver(MyObserver){

    }

    private  volatile ArrayList<MyObserver>observers = new ArrayList<MyObserver>();
    public void addObserver(MyObserver observer){
        observers.add(observer);
    }

    private void notifyAllObservers(){
        for(MyObserver observer: observers){
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable obersevable){
        notifyAllObservers();
    }
    /*public boolean HasTweet(Tweet tweet) {
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).equals(tweet)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList getTweet(){
        return tweets;
    }

    public void delete(Tweet tweet){
        needRemoveTweet = tweet;
        tweets.remove(tweet);
    }*/

}

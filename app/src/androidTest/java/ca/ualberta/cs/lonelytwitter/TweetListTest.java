package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by thu2 on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
    public  TweetListTest(){
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    //not suggest to use these 2 methods
   /* public void setUp(){

    }

    public void tearDown(){

    }*/

    public void testHoldsStuff(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);

    }
    public void testHoldsManyThings(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.count(),2);

    }
    public void testAddTweets(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
    }
    public void testGetTweets(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        list.add(new NormalTweet("test"));
        list.add(new NormalTweet("test"));
        list.getTweet();
    }
    public void testHasTweets(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        list.HasTweet(tweet);
    }

    public void testRemoveTweet(){
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.delete(tweet);
    }

    public void testCountUpTweet(){
        TweetList list = new TweetList();
        list.count();
    }
}
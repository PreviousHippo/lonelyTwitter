package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }
    public void testStart() throws Exception{
        Activity activity = getActivity();
    }

    private EditText bodyText;
    public void testEditATest(){
        LonelyTwitterActivity activity =(LonelyTwitterActivity)getActivity();
        activity.getTweets().clear();
        //user click on tweet they want to edit it
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburger");
            }
        });
        getInstrumentation().waitForIdleSync();//make sure our UI thread finish

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();//make sure our UI thread finish
        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet)oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburger",tweet.getText());

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

//------------------------------------------------------------------------------------------------------------------------------------------------>
        // Following was stolen from https://developer.android.com/training/activity-testing/activity-functional-testing.html 2015-10-13

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
//---------------------------------------------------------------------------------------------------------------------------------------------------->

        //assert that the tweet being shown on the edit screen in the tweet
        //we clicked on

        //edit the text of that tweet
        bodyText = activity.getBodyText();
        tweet = (Tweet)oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburger",tweet.getText());
        //save our edit

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //assert that our edits were saved into tweet correctly

        //assert that our edits are shown on the screen to the user
        //back in he main activity

        // end of test: clear the data
        // end of test: make sure the edit activity is closed
        receiverActivity.finish();
    }
}
package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.xml.transform.Result;

public class LonelyTwitterActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private LonelyTwitterActivity activity = this;
    private EditText bodyText;
    private ListView oldTweetsList;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayAdapter<Tweet> adapter;
    private Button saveButton;


    public EditText getBodyText() {
        return bodyText;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public ListView getOldTweetsList() {
        return oldTweetsList;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);//view

        bodyText = (EditText) findViewById(R.id.body);//view
        saveButton = (Button) findViewById(R.id.save);//view
        Button clearButton = (Button) findViewById(R.id.clear);//view
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();//controller
                tweets.add(new NormalTweet(text));//controller
                adapter.notifyDataSetChanged();//view
                saveInFile();//model
                //saveInFile(text, new Date(System.currentTimeMillis()));
                //finish();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                tweets.clear();//view
                adapter.notifyDataSetChanged();//view
                clearInFile();//model
            }


        });

        oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                Intent intent = new Intent(activity, EditTweetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();//model
        //ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);//view
        oldTweetsList.setAdapter(adapter);//view
    }

    private void loadFromFile() {
        //ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);//model
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));//model
            Gson gson = new Gson();//controller
            //taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {
            }.getType();//controller
           tweets = gson.fromJson(in, listType);//model


            //String line = in.readLine();
            //while (line != null) {
            //	tweets.add(line);
            //	line = in.readLine();
            //}

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            tweets = new ArrayList<Tweet>();//model
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);//model
        }
        //return tweets.toArray(new String[tweets.size()]);
    }

    private void saveInFile() {//model
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(tweets, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void clearInFile() {//model
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(tweets, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }

}
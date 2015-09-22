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
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.xml.transform.Result;

public class LonelyTwitterActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldTweetsList;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayAdapter<Tweet> adapter;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bodyText = (EditText) findViewById(R.id.body);
        Button saveButton = (Button) findViewById(R.id.save);
        Button clearButton = (Button) findViewById(R.id.clear);
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                tweets.add(new NormalTweet(text));
                adapter.notifyDataSetChanged();
                saveInFile();
                //saveInFile(text, new Date(System.currentTimeMillis()));
                //finish();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                tweets.clear();
                adapter.notifyDataSetChanged();
                clearInFile();
            }


        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        //ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);
        oldTweetsList.setAdapter(adapter);
    }

    private void loadFromFile() {
        //ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {
            }.getType();
           tweets = gson.fromJson(in, listType);


            //String line = in.readLine();
            //while (line != null) {
            //	tweets.add(line);
            //	line = in.readLine();
            //}

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            tweets = new ArrayList<Tweet>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        //return tweets.toArray(new String[tweets.size()]);
    }

    private void saveInFile() {
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

    private void clearInFile() {
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
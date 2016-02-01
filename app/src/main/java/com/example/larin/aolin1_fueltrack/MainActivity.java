package com.example.larin.aolin1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    public final static String KEY = "com.example.larin.aolin1_fueltrack.index";
    private ListView FuelView;
    private ArrayList<Entrylist> Entrylists = new ArrayList<Entrylist>();
    private ArrayAdapter<Entrylist> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FuelView = (ListView)findViewById(R.id.listView);
        //clear all data
        Button clear = (Button)findViewById(R.id.Clear);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.clear();
                saveInFile();
            }
        });
    }
    protected void onStart(){
        super.onStart();
        loadFromFile();
        //create adapter
        adapter = new ArrayAdapter<Entrylist>(this,
                R.layout.list_item, Entrylists);
        FuelView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        FuelView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //I trid to pass the data to EditEntry, but failed.
                Intent intents = new Intent(MainActivity.this, EditEntry.class);
                intents.putExtra(KEY, position);
                startActivity(intents);
            }
        });
    }
    //click Button ADD entry, create new intent
    public void onButtonClick(View view) {
        Intent intent = new Intent(this, AddEntry.class);
        startActivity(intent);
    }

    //copy from lonely tweet
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html Jan-21-2016
            Type listType = new TypeToken<ArrayList<Entrylist>>() {
            }.getType();
            Entrylists = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Entrylists = new ArrayList<Entrylist>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    //copy from lonely tweet
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(Entrylists, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}

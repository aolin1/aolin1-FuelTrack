package com.example.larin.aolin1_fueltrack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddEntry extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Entrylist> Entrylists = new ArrayList<Entrylist>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        //cancel button back to MainActivity
        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //making new entry
        Button add_entry = (Button)findViewById(R.id.add_entry);
        add_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFromFile();
                saveEntry();
            }
        });

    }

    public void saveEntry() {
        EditText new_date = (EditText) findViewById(R.id.date);
        String date = new_date.getText().toString();
        EditText new_station = (EditText) findViewById(R.id.station);
        String station = new_station.getText().toString();
        EditText new_odometer = (EditText) findViewById(R.id.odometer);
        String odometer = new_odometer.getText().toString();
        EditText new_grade = (EditText) findViewById(R.id.fuel_grade);
        String grade = new_grade.getText().toString();
        EditText new_amount = (EditText) findViewById(R.id.fuel_amount);
        float amount = Float.valueOf(new_amount.getText().toString());
        EditText new_cost = (EditText) findViewById(R.id.unit_cost);
        float cost = Float.valueOf(new_cost.getText().toString());

        Entrylist Entrylist = new Entrylist(date, station, odometer, grade, amount, cost);
        Entrylists.add(Entrylist);
        saveInFile();
        finish();

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

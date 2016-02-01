package com.example.larin.aolin1_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EditEntry extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Entrylist> Entrylists = new ArrayList<Entrylist>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFromFile();
        //getting data from MainActivity,soenthing goes wrong here.
        Intent intents = getIntent();
        final int index = Integer.parseInt(intents.getStringExtra(MainActivity.KEY));
        findEntry(index);

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Entrylists.remove(index);
                Entrylists.add(index, Entrylist);
                Entrylists.add(Entrylist);
                saveInFile();
                finish();
            }
        });
    }
    private void findEntry(int index){
        Entrylist Entrylist = Entrylists.get(index);

        EditText new_date = (EditText) findViewById(R.id.date);
        new_date.setText(Entrylist.getDate().toString());
        EditText new_station = (EditText) findViewById(R.id.station);
        new_station.setText(Entrylist.getStation().toString());
        EditText new_odometer= (EditText) findViewById(R.id.odometer);
        new_odometer.setText(Entrylist.getOdometer().toString());
        EditText new_grade = (EditText) findViewById(R.id.fuel_grade);
        new_grade.setText(Entrylist.getGrade().toString());
        EditText new_amount = (EditText) findViewById(R.id.fuel_amount);
        new_amount.setText(Entrylist.getAmount().toString());
        EditText new_cost = (EditText) findViewById(R.id.unit_cost);
        new_cost.setText(Entrylist.getCost().toString());


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

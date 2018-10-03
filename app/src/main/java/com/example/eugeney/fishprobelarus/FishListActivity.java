package com.example.eugeney.fishprobelarus;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;

public class FishListActivity extends AppCompatActivity {

    List<String> fishImage = new ArrayList<>();
    public static List<InformationFish> fishes = new ArrayList<>();
//--------------------------------------------------------------------------------------------------
    ListView fishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        fishList = findViewById(R.id.fishList);

        FishAdapter fishAdapter = new FishAdapter(this, R.layout.item_list, fishes);
        fishList.setAdapter(fishAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FishInfo.class);
                intent.putExtra("id", id+1);
                startActivity(intent);
            }
        };
        fishList.setOnItemClickListener(itemListener);
    }


}

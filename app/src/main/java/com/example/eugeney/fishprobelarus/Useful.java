package com.example.eugeney.fishprobelarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Useful extends AppCompatActivity {

    public static List<InformationUseful> useful = new ArrayList<>();

    ListView usefulList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful);

        usefulList = findViewById(R.id.usefulList);

        UsefulAdapter usefulAdapter = new UsefulAdapter(this, R.layout.item_list, useful);
        usefulList.setAdapter(usefulAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UsefulInfo.class);
                intent.putExtra("id", id+1);
                startActivity(intent);
            }
        };
        usefulList.setOnItemClickListener(itemListener);
    }
}

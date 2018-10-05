package com.example.eugeney.fishprobelarus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Reservoirs extends AppCompatActivity {

    public static List<InformationReservoir> reservoirs = new ArrayList<>();

    ListView reservoirList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservoirs);
        reservoirList = findViewById(R.id.reservoirList);

        ReservoirsAdapter reservoirsAdapter = new ReservoirsAdapter(this, R.layout.reservoirs_list, reservoirs);
        reservoirList.setAdapter(reservoirsAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ReservoirInfo.class);
                intent.putExtra("id", id+1);
                startActivity(intent);
            }
        };
        reservoirList.setOnItemClickListener(itemListener);
    }
}

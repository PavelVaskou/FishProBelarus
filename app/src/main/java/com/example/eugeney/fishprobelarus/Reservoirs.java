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

    List<String> reservoirImage = new ArrayList<>();
    public static List<InformationReservoir> reservoirs = new ArrayList<>();
    //--------------------------------------------------------------------------------------------------
    ListView reservoirList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservoirs);

    }
}

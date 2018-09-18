package com.example.eugeney.fishprobelarus;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FishListActivity extends AppCompatActivity {
    InputStream mInputStream;

    int[] integers = null;

    String[] fishCSV;
    List<String> fishName = new ArrayList<>();
    List<String> fishImage = new ArrayList<>();
    List<InformationFish> fishes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        new ProgressTask().execute();


        ListView fishList = findViewById(R.id.fishList);

        FishAdapter fishAdapter = new FishAdapter(this, R.layout.item_list, fishes);

        fishList.setAdapter(fishAdapter);

    }

    public void readCSV(){
        mInputStream = getResources().openRawResource(R.raw.fishs_list);

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                fishCSV = csvLine.split(";");
               /* try {
                    Log.e("Date"," " + fishCSV[0] + " " + fishCSV[1] + " " + fishCSV[2] + " " + fishCSV[3]);
                }catch (Exception e){
                    Log.e("Problem", e.toString());
                }*/
                fishes.add(new InformationFish(fishCSV[0],fishCSV[1],fishCSV[2],fishCSV[3]));
            }
        }
        catch (IOException ex){
            throw new RuntimeException("Error in resding CSV file: "+ ex);
        }
    }

    private void arrFishInfo(){
        for (int i = 1; i< fishes.size(); i++){
            fishName.add(fishes.get(i).name);
            fishImage.add(fishes.get(i).image);
        }
    }
    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
                readCSV();
                arrFishInfo();
            return(null);
        }
        @Override
        protected void onProgressUpdate(Integer... items) {

        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Загрузка завершена", Toast.LENGTH_SHORT)
                    .show();
        }
    }
}

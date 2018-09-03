package com.example.eugeney.fishprobelarus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FishList extends AppCompatActivity {
    InputStream mInputStream;

    String[] fishCSV;
    List<String> fishName = new ArrayList<>();
    List<InformationFish> fishes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        readCSV();
        arrFishIfo();
        // получаем элемент ListView
        ListView fishList = (ListView) findViewById(R.id.fishList);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, fishName);

        // устанавливаем для списка адаптер
        fishList.setAdapter(adapter);

    }

    public void readCSV(){
        mInputStream = getResources().openRawResource(R.raw.fishs_list);

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                fishCSV = csvLine.split(";");
                try {
                    Log.e("Date"," " + fishCSV[0] + " " + fishCSV[1] + " " + fishCSV[2] + " " + fishCSV[3]);
                }catch (Exception e){
                    Log.e("Problem", e.toString());
                }

                fishes.add(new InformationFish(fishCSV[0],fishCSV[1],fishCSV[2],fishCSV[3]));
            }
        }
        catch (IOException ex){
            throw new RuntimeException("Error in resding CSV file: "+ ex);
        }
    }

    private void arrFishIfo(){
        for (int i = 1; i< fishes.size(); i++){
            fishName.add(fishes.get(i).name);
        }
    }

   /* protected String doInBackground(Void... voids) {
        String json = null;
        try {
            InputStream is = getAssets().open("pisces.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        System.out.print(json);
        return json;
    }
    protected void onPostExecute(String strJson) {

        JSONObject dataJsonObj = null;
        String secondName = "";
        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray pisces = dataJsonObj.getJSONArray("pisces");

            for (int i = 0; i < pisces.length(); i++) {
                JSONObject fish = pisces.getJSONObject(i);

                String name = fish.getString("name");
                String text = fish.getString("text");
                String date = fish.getString("date");

                informationFish.add(new InformationFish(name,text, date));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

}

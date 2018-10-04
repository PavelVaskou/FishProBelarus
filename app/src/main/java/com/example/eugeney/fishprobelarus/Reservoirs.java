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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;
import database.DatabaseHelperReservoirs;

public class Reservoirs extends AppCompatActivity {

    InputStream mInputStream;

    // номера ячеек массива
    final int countId = 0;
    final int countName = 1;
    final int countInfo = 2;
    final int countImage = 3;

    String[] fishCSV;

    DatabaseHelperReservoirs databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    List<String> reservoirImage = new ArrayList<>();
    public static List<InformationReservoir> reservoirs = new ArrayList<>();
    //--------------------------------------------------------------------------------------------------
    ListView reservoirList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservoirs);

        databaseHelper = new DatabaseHelperReservoirs(this);
        db = databaseHelper.getWritableDatabase();


        new ProgressTask().execute();

        reservoirList = findViewById(R.id.reservoirList);

        ReservoirsAdapter reservoirsAdapte = new ReservoirsAdapter(this, R.layout.item_list, reservoirs);
        reservoirList.setAdapter(reservoirsAdapte);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FishInfo.class);
                intent.putExtra("id", id+1);
                startActivity(intent);
            }
        };
        reservoirList.setOnItemClickListener(itemListener);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение
        db.close();

    }
    // считывание БД и вывод в LOG
    public void readDB(){
        cursor = db.query(DatabaseHelperReservoirs.TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelperReservoirs.COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(DatabaseHelperReservoirs.COLUMN_NAME);
            int infoIndex = cursor.getColumnIndex(DatabaseHelperReservoirs.COLUMN_INFO);
            int imageIndex = cursor.getColumnIndex(DatabaseHelperReservoirs.COLUMN_IMAGE);
            do {
               // reservoirs.add(new InformationReservoir(String.valueOf(cursor.getInt(idIndex)),cursor.getString(nameIndex), cursor.getString(infoIndex), cursor.getString(imageIndex)));
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", info = " + cursor.getString(infoIndex) +
                        ", Image = " + cursor.getString(imageIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();
        goHome();
    }
    // Парсинг CSV  и записваем в БД
    public void readCSV(){
        mInputStream = getResources().openRawResource(R.raw.reservoirs);

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                fishCSV = csvLine.split(";");

                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelperReservoirs.COLUMN_ID, fishCSV[countId]);
                cv.put(DatabaseHelperReservoirs.COLUMN_NAME, fishCSV[countName]);
                cv.put(DatabaseHelperReservoirs.COLUMN_INFO, fishCSV[countInfo]);
                cv.put(DatabaseHelperReservoirs.COLUMN_IMAGE, fishCSV[countImage]);
                db.insert(DatabaseHelperReservoirs.TABLE, null, cv);
            }
        }
        catch (IOException ex){
            throw new RuntimeException("Error in resding CSV file: "+ ex);
        }
    }

    //Второй паток - внего входит парсинг, запись и считывание БД
    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            readCSV();
            readDB();

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
    private void goHome(){
        // закрываем подключение
        db.close();
    }
}

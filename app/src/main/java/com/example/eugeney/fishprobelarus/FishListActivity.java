package com.example.eugeney.fishprobelarus;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    InputStream mInputStream;

    // номера ячеек массива
    final int countId = 0;
    final int countName = 1;
    final int countInfo = 2;
    final int countImage = 3;

    String[] fishCSV;
    List<String> fishName = new ArrayList<>();
    List<String> fishImage = new ArrayList<>();
    public static List<InformationFish> fishes = new ArrayList<>();
//--------------------------------------------------------------------------------------------------
    ListView listFish;
    TextView header;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter userAdapter;
    long userId=0;
//--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        header = findViewById(R.id.header);
        listFish = findViewById(R.id.list);
        databaseHelper = new DatabaseHelper(this);

        db = databaseHelper.getWritableDatabase();

        new ProgressTask().execute();

    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение
        db.close();

    }
// считывание БД и вывод в LOG
    public void readDB(){
        cursor = db.query(DatabaseHelper.TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            int infoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_INFO);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", info = " + cursor.getString(infoIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();
        goHome();
    }
// Парсинг CSV  и записваем в БД
    public void readCSV(){
        mInputStream = getResources().openRawResource(R.raw.fishs_list);

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                fishCSV = csvLine.split(";");

                ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_ID, fishCSV[countId]);
                    cv.put(DatabaseHelper.COLUMN_NAME, fishCSV[countName]);
                    cv.put(DatabaseHelper.COLUMN_INFO, fishCSV[countInfo]);
                    cv.put(DatabaseHelper.COLUMN_IMAGE, fishCSV[countImage]);

                db.insert(DatabaseHelper.TABLE, null, cv);
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

package com.example.eugeney.fishprobelarus;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import database.DatabaseHelper;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    InputStream mInputStream;

    // номера ячеек массива
    final int countId = 0;
    final int countName = 1;
    final int countInfo = 2;
    final int countImage = 3;

    String[] objCSV;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        new ProgressTask().execute();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_reservoirs) {
            Intent intent = new Intent(this, Reservoirs.class);
            startActivity(intent);
        } else if (id == R.id.nav_fish) {
            Intent intent = new Intent(this, FishListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_rigging) {
            Intent intent = new Intent(this, Rigging.class);
            startActivity(intent);
        } else if (id == R.id.nav_maps) {
            Intent intent = new Intent(this, Maps.class);
            startActivity(intent);
        } else if (id == R.id.nav_useful) {
            Intent intent = new Intent(this, Useful.class);
            startActivity(intent);
        }else if (id == R.id.nav_share) {

        }else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение
        db.close();

    }
    // считывание БД и вывод в LOG
    public void readDBFish(){
        cursor = db.query(DatabaseHelper.TABLE_FISH, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            int infoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_INFO);
            int imageIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE);
            do {
                FishListActivity.fishes.add(new InformationFish(String.valueOf(cursor.getInt(idIndex)),cursor.getString(nameIndex), cursor.getString(infoIndex), cursor.getString(imageIndex)));

            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        //cursor1.close();
       // goHome();
    }

    public void readDBReservoirs(){
        cursor = db.query(DatabaseHelper.TABLE_RESERVOIRS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            int infoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_INFO);
            int imageIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE);
            do {
                Reservoirs.reservoirs.add(new InformationReservoir(String.valueOf(cursor.getInt(idIndex)),cursor.getString(nameIndex), cursor.getString(infoIndex), cursor.getString(imageIndex)));

            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();
        // goHome();
    }
    // Парсинг CSV  и записваем в БД
    public void readCSVFish(){
        mInputStream = getResources().openRawResource(R.raw.mycsvfile);

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                objCSV = csvLine.split(";");

                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_ID, objCSV[countId]);
                cv.put(DatabaseHelper.COLUMN_NAME, objCSV[countName]);
                cv.put(DatabaseHelper.COLUMN_INFO, objCSV[countInfo]);
                cv.put(DatabaseHelper.COLUMN_IMAGE, objCSV[countImage]);
                db.insert(DatabaseHelper.TABLE_FISH, null, cv);
            }
        }
        catch (IOException ex){
            throw new RuntimeException("Error in resding CSV file: "+ ex);
        }
    }

    public void readCSVReservoirs(){
        mInputStream = getResources().openRawResource(R.raw.reservoirs);

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String csvLine;
            while ((csvLine = reader.readLine()) != null){
                objCSV = csvLine.split(";");

                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_ID, objCSV[countId]);
                cv.put(DatabaseHelper.COLUMN_NAME, objCSV[countName]);
                cv.put(DatabaseHelper.COLUMN_INFO, objCSV[countInfo]);
                cv.put(DatabaseHelper.COLUMN_IMAGE, objCSV[countImage]);
                db.insert(DatabaseHelper.TABLE_RESERVOIRS, null, cv);
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
            readCSVReservoirs();
            readDBReservoirs();
            readCSVFish();
            readDBFish();

            goHome();
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

package com.example.eugeney.fishprobelarus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import database.DatabaseHelper;

public class UsefulInfo extends AppCompatActivity {

    public final int NAME = 1;
    public final int INFO = 2;
    public final int IMAGE = 3;

    TextView name;
    ImageView image;
    TextView info;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_info);

        name = findViewById(R.id.name);
        image = findViewById(R.id.image);
        info = findViewById(R.id.info);

        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }

        // получаем элемент по id из бд
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE_USEFUL + " where " +
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        userCursor.moveToFirst();

        //заполнение View--------------СМОТРЕТЬ  СЮДА------------------------------------------
        name.setText(userCursor.getString(NAME));
        String resImage = userCursor.getString(IMAGE);
        int id = image.getResources().getIdentifier(resImage, "drawable", "com.example.eugeney.fishprobelarus");
        image.setImageResource(id);
        String textInfo = userCursor.getString(INFO);
        info.setText(Html.fromHtml(textInfo));
        userCursor.close();
    }
}

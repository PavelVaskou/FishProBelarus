package database;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "fishPro"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE_FISH = "fish"; // название таблицы в бд
    public static final String TABLE_RESERVOIRS = "reservoirs";
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INFO= "info";
    public static final String COLUMN_IMAGE= "image";

    static final String CREAT_TABLEFISH = "CREATE TABLE " + TABLE_FISH + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_INFO + " TEXT," + COLUMN_IMAGE + " TEXT" +")";
    static final String CREAT_TABLERESERVOIRS = "CREATE TABLE " + TABLE_RESERVOIRS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_INFO + " TEXT," + COLUMN_IMAGE + " TEXT" +")";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAT_TABLEFISH);
        db.execSQL(CREAT_TABLERESERVOIRS);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_FISH + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_RESERVOIRS + "'");
        onCreate(db);
    }
}

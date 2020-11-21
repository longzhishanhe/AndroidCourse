package com.example.english_words01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WordsDBHelper extends SQLiteOpenHelper {
    private final static String TAG = "mytag";
    //数据库名
    private final static String DATABASE_NAME = "wordsdb";
    private final static int DATABASE_VERSION = 1;

    //建表
    private final static String SQL_CREATE_DATABASE = "CREATE TABLE " + Words.Word.TABLE_NAME + " (" +
            Words.Word._ID + " VARCHAR(32) PRIMARY KEY NOT NULL," +
            Words.Word.COLUMN_NAME_WORD + " TEXT UNIQUE NOT NULL,"+
            Words.Word.COLUMN_NAME_MEANING + " TEXT,"
            + Words.Word.COLUMN_NAME_SAMPLE + " TEXT)";
    //删表
    private final static String SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS "
            + Words.Word.TABLE_NAME;

    public WordsDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG,"db onCreate");
        db.execSQL(SQL_CREATE_DATABASE);
        Log.v(TAG,"creat db");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }

}

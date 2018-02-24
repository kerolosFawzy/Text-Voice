package com.massive.voicetext.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.massive.voicetext.Utlis.Constant;



public class SqliteHelperClass extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = Constant.PATH;
    private static final int DATABASE_VERSION = 1;

    public SqliteHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constant.Entry.DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable = "CREATE TABLE " + Constant.Entry.DATABASE_TABLE + " ("
                + Constant.Entry.ID + " varchar(20) PRIMARY KEY,"
                + Constant.Entry.TEXT + " varchar(1000)"
                + " )";
        db.execSQL(creatTable);
    }
}
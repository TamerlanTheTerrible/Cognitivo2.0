package com.example.timur.mainmenu.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Timur on 4/4/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    static final String DATABASE_NAME = "cognitivodb";
    static final int DATABASE_VERSION = 5;
    static final int NAME_COLUMN = 1;
    private static final String CREATE_USER_TABLE = "CREATE TABLE "+"User "
            +"( "+"RegID INTEGER PRIMARY KEY AUTOINCREMENT, "+"Firstname TEXT, "+"Lastname TEXT, "
            +"DOB text, "+"Country text, "+"Username text, "+"Password text, "+"Email text );";
    private static final String CREATE_COLORMATCH_TABLE = "create table Colormatch ( "
            +"cmID integer primary key autoincrement, "+"Username text, "+"Date text, "+"Score integer, "+"WrongAnswer integer);";
    private static final String CREATE_RAINGAME_TABLE = "create table Raingame ( "
            +"rainID integer primary key autoincrement, "+"Username text, "+"Date text, "+"Score integer, "+"WrongAnswer integer);";
    private static final String CREATE_CARDGAME_TABLE = "create table Cardgame ("
            +"cardID integer primary key autoincrement, "+"Username text, "+"Date text, "+"Score integer, "+"SpentTime integer);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CARDGAME_TABLE);
        db.execSQL(CREATE_COLORMATCH_TABLE);
        db.execSQL(CREATE_RAINGAME_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+"User");
        db.execSQL("DROP TABLE IF EXISTS "+"Colormatch");
        db.execSQL("DROP TABLE IF EXISTS "+"Raingame");
        db.execSQL("DROP TABLE IF EXISTS "+"Cardgame");
        onCreate(db);
    }
}

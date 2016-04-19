package com.example.timur.mainmenu.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListAdapter;

import com.example.timur.mainmenu.model.Card;
import com.example.timur.mainmenu.model.Cardgame;
import com.example.timur.mainmenu.model.Colormatch;
import com.example.timur.mainmenu.model.Raingame;
import com.example.timur.mainmenu.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Timur on 4/5/2016.
 */
public class DBservice {
    private SQLiteDatabase db;
    private final Context context;
    private DBHelper dbHelper;
    public  DBservice(Context _context){
        context =_context;
        dbHelper = new DBHelper(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
    }

    public DBservice open(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDBInstance(){
        return db;
    }

    public boolean isTableEmpty(){
        boolean bool = true;
        Cursor c = db.query("User", null, null, null, null, null, null);
        if (c.getCount()>0){
            bool = false;
        }
        return bool;
    }

    public void insertUser(String fname, String lname, String dob, String country,
                           String email, String username, String pswrd){
        ContentValues cv = new ContentValues();
        cv.put("Firstname", fname);
        cv.put("Lastname", lname);
        cv.put("DOB", dob);
        cv.put("Country", country);
        cv.put("Email", email);
        cv.put("Username", username);
        cv.put("Password", pswrd);
        db.insert("User", null, cv);
    }

    public void updateUser(String fname, String lname, String dob, String country,
                           String email, String username, String pswrd) {
        ContentValues cv = new ContentValues();
        cv.put("Firstname", fname);
        cv.put("Lastname", lname);
        cv.put("DOB", dob);
        cv.put("Country", country);
        cv.put("Email", email);
        cv.put("Username", username);
        cv.put("Password", pswrd);
        String where = "Username = ?";
        db.update("User", cv, where, new String[]{username});
    }

    public boolean userExists(String username){
        boolean bool=false;
        Cursor c  = db.query("User", null, "Username = ?", new String[]{username}, null, null, null);
        if ((c.getCount()>0)){
            bool = true;
        }
        return bool;
    }

    public boolean login(String username, String password){
        boolean bool=false;
        Cursor c  = db.query("User", null, "Username = ?", new String[]{username}, null, null, null);
        if(c.moveToFirst()){
            int pswrdColIndex = c.getColumnIndex("Password");
            do{
               if(password.equals(c.getString(pswrdColIndex))){
                   bool = true;
               }
            }while (c.moveToNext());
        }
        return bool;
    }

    public User getUser(String username){
        Cursor c  = db.query("User", null, "Username = ?", new String[]{username}, null, null, null);
        User user = new User();
        if(c.moveToFirst()){
            int fname = c.getColumnIndex("Firstname");
            int lname = c.getColumnIndex("Lastname");
            int dob = c.getColumnIndex("DOB");
            int country = c.getColumnIndex("Country");
            int email = c.getColumnIndex("Email");
            do{
                user.setFirstname(c.getString(fname));
                user.setLastname(c.getString(lname));
                user.setDOB(c.getString(dob));
                user.setCountry(c.getString(country));
                user.setEmail(c.getString(email));
            }while (c.moveToNext());
        }

        return user;
    }

    public void inserColormatch(int score, int wa, String username){
        Calendar c = Calendar.getInstance();
        String date = Integer.toString(c.get(Calendar.DATE));
        ContentValues cv = new ContentValues();
        cv.put("Score", score);
        cv.put("WrongAnswer", wa);
        cv.put("Username", username);
        cv.put("Date", date);
        db.insert("Colormatch", null, cv);
    }

    public void insertRaingame(int score, int wa, String username){
        Calendar c = Calendar.getInstance();
        String date = Integer.toString(c.get(Calendar.DATE));
        ContentValues cv = new ContentValues();
        cv.put("Score", score);
        cv.put("WrongAnswer", wa);
        cv.put("Username", username);
        cv.put("Date", date);
        db.insert("Raingame", null, cv);
    }

    public List<Raingame> getAllRainGames(){
        Cursor c = db.query("Colormatch", null, null, null, null, null, "Score");
        List<Raingame> raingameList = new ArrayList<>();
        if(c.moveToFirst()){
            int scoreCol = c.getColumnIndex("Score");
            int userCol = c.getColumnIndex("Username");
            int wrongCol = c.getColumnIndex("WrongAnswer");
            int dateCol = c.getColumnIndex("Date");
            do{
                int score = c.getInt(scoreCol);
                String name = c.getString(userCol);
                int wrong = c.getInt(wrongCol);
                String date = c.getString(dateCol);
                Raingame cm = new Raingame(score, wrong, name, date);
                raingameList.add(cm);
            }while ((c.moveToNext()));
        }
        return raingameList;
    }

    public List<Colormatch> getAllColormatches(){
        Cursor c = db.query("Colormatch", null, null, null, null, null, "Score");
        List<Colormatch> colormatchArray = new ArrayList<>();
        if(c.moveToFirst()){
            int scoreCol = c.getColumnIndex("Score");
            int userCol = c.getColumnIndex("Username");
            int wrongCol = c.getColumnIndex("WrongAnswer");
            int dateCol = c.getColumnIndex("Date");
            do{
                int score = c.getInt(scoreCol);
                String name = c.getString(userCol);
                int wrong = c.getInt(wrongCol);
                String date = c.getString(dateCol);
                Colormatch cm = new Colormatch(score, wrong, name, date);
                colormatchArray.add(cm);
            }while ((c.moveToNext()));
        }
        return colormatchArray;
    }

    public List<Cardgame> getAllCardgames(){
        Cursor c = db.query("Cardgame", null, null, null, null, null, "SpentTime");
        List<Cardgame> cardgameList = new ArrayList<>();
        if(c.moveToFirst()){
            int scoreCol = c.getColumnIndex("Score");
            int userCol = c.getColumnIndex("Username");
            int timeCol = c.getColumnIndex("SpentTime");
            int dateCol = c.getColumnIndex("Date");
            do{
                int score = c.getInt(scoreCol);
                String name = c.getString(userCol);
                int time = c.getInt(timeCol);
                String date = c.getString(dateCol);
                Cardgame cg = new Cardgame(score, time, name, date);
                cardgameList.add(cg);
            }while ((c.moveToNext()));
        }
        return cardgameList;
    }

    public void inserCardgame(int score, int time, String username){
        Calendar c = Calendar.getInstance();
        String date = Integer.toString(c.get(Calendar.DATE));
        ContentValues cv = new ContentValues();
        cv.put("Score", score);
        cv.put("SpentTime", time);
        cv.put("Username", username);
        cv.put("Date", date);
        db.insert("Cardgame", null, cv);
    }
}

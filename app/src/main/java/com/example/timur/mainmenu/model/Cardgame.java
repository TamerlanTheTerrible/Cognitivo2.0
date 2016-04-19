package com.example.timur.mainmenu.model;

/**
 * Created by Timur on 4/6/2016.
 */
public class Cardgame {
    private int cgID;
    private String date;
    private int turns;
    private int spentTime;
    private String userName;

    public Cardgame(int _score, int _spentTime, String _userName, String _date){
        date = _date;
        turns = _score;
        spentTime = _spentTime;
        userName = _userName;
    }

    public Cardgame(){}

    public String getDate(){
        return date;
    }

    public void setDate(String _date){
        date = _date;
    }

    public int getTurns(){
        return turns;
    }

    public void setTurns(int _score){
        turns = _score;
    }

    public int getSpentTime(){
        return spentTime;
    }

    public void setSpentTime(int _spentTime){
        spentTime = _spentTime;
    }

    public String getUsername(){
        return userName;
    }

    public void setUsername(String _userName){
        userName = _userName;
    }
}

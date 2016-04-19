package com.example.timur.mainmenu.model;

/**
 * Created by Timur on 4/6/2016.
 */
public class Raingame {
    private int rgID;
    private String date;
    private int score;
    private int wrongAnswers;
    private String username;

    public Raingame(int _score, int _wrongAnswers, String _userId, String _date){
        date = _date;
        score = _score;
        wrongAnswers = _wrongAnswers;
        username = _userId;
    }

    public Raingame(){}

    public String getDate(){
        return date;
    }

    public void setDate(String _date){
        date = _date;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int _score){
        score = _score;
    }

    public int getWrongAnswers(){
        return wrongAnswers;
    }

    public void setWrongAnswers(int _wrongAnswers){
        wrongAnswers = _wrongAnswers;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String _username){
        username = _username;
    }
}

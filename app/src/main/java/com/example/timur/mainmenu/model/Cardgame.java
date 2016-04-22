package com.example.timur.mainmenu.model;

/**
 * Created by Timur on 4/6/2016.
 */
public class Cardgame {
    private int cgID;
    private String date;
    private int score;
    private int wrongAnswer;
    private String userName;

    public Cardgame(int _score, int _spentTime, String _userName, String _date){
        date = _date;
        score = _score;
        wrongAnswer = _spentTime;
        userName = _userName;
    }

    public Cardgame(){}

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

    public int getWrongAnswer(){
        return wrongAnswer;
    }

    public void setWrongAnswer(int _spentTime){
        wrongAnswer = _spentTime;
    }

    public String getUsername(){
        return userName;
    }

    public void setUsername(String _userName){
        userName = _userName;
    }
}

package com.example.timur.mainmenu.model;

import android.widget.TextView;

/**
 * Created by Timur on 3/5/2016.
 */
public class Drop {
    //Context context;
    private TextView drop;
    private int a;
    private int b;
    private int answer = a+b;

    public TextView getView(){
        return drop;
    }

    public void setView(TextView view){
        drop = view;
    }

    public int getA(){
        return a;
    }

    public void setA(int a){
        this.a = a;
    }

    public int getB(){
        return b;
    }

    public void setB(int b){
        this.b = b;
    }

    public int getAnswer(){
        answer = a+b;
        return answer;
    }

    public void setAnswer(int answer){
        this.answer = answer;
    }

}

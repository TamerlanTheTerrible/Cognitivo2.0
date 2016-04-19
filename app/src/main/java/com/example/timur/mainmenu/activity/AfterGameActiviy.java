package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.service.DBservice;

/**
 * Created by Timur on 4/4/2016.
 */
public class AfterGameActiviy extends BaseActivity{
    private TextView txtScore, txtAnotherGame, txtReplay, txtMainMenu;
    DBservice dbService;
    Intent intent;
    int cmScore, cmWrongAnswer, cgScore, cgTime, rgScore, rgWrongAnswer, score, wrongAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_aftergame_layout);

        dbService = new DBservice(this);
        dbService = dbService.open();

        txtAnotherGame = (TextView)findViewById(R.id.txtNextGame);
        txtMainMenu = (TextView)findViewById(R.id.txtMainMenu);
        txtReplay = (TextView)findViewById(R.id.txtReplay);
        txtScore = (TextView)findViewById(R.id.txtScore);

        intent = getIntent();
        cmScore = intent.getIntExtra("cmScore", -1);
        cmWrongAnswer = intent.getIntExtra("cmWrongAnswer", -1);
        cgScore = intent.getIntExtra("cgScore", -1);
        cgTime = intent.getIntExtra("cgTurns", -1);
        rgScore = intent.getIntExtra("rgScore", -1);
        rgWrongAnswer = intent.getIntExtra("rgWrongAnswer", -1);
        if(!(cmScore==-1)){
            score = cmScore;
            wrongAnswer = cmWrongAnswer;
        }
        else if(!(cgScore==-1)){
            score = cgScore;
            wrongAnswer = cgTime;
        }
        else {
            score = rgScore;
            wrongAnswer = rgWrongAnswer;
        }

        txtScore.setText(Integer.toString(score));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                switch (v.getId()){
                    case R.id.txtNextGame:
                        intent1 = new Intent(AfterGameActiviy.this, GamesScreen.class);
                        if(!(cgScore==-1)){
                            dbService.inserCardgame(score, wrongAnswer, USERNAME);
                            Toast.makeText(getApplication(), "Cardgame", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(rgScore==-1)){
                            dbService.insertRaingame(score, wrongAnswer, USERNAME);
                            Toast.makeText(getApplication(), "Raingame", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dbService.inserColormatch(score, wrongAnswer, USERNAME);
                            Toast.makeText(getApplication(), "Colormatch", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.txtMainMenu:
                        intent1 = new Intent(AfterGameActiviy.this, MainActivity.class);
                        if(!(cgScore==-1)){
                            dbService.inserCardgame(score, wrongAnswer, USERNAME);
                            Toast.makeText(getApplication(), "Cardgame", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(rgScore==-1)){
                            dbService.insertRaingame(score, wrongAnswer, USERNAME);
                            Toast.makeText(getApplication(), "Raingame", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dbService.inserColormatch(score, wrongAnswer, USERNAME);
                            Toast.makeText(getApplication(), "Colormatch", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.txtReplay:
                        intent1 = new Intent(AfterGameActiviy.this, ColorMatchActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                }
            }
        };
        txtReplay.setOnClickListener(listener);
        txtMainMenu.setOnClickListener(listener);
        txtAnotherGame.setOnClickListener(listener);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbService.close();
    }
}

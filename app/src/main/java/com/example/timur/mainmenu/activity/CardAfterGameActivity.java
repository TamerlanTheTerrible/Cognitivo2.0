package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.LoginActivityTest;
import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.RestAPI;
import com.example.timur.mainmenu.UserDetailsTable;
import com.example.timur.mainmenu.model.Cardgame;
import com.example.timur.mainmenu.model.Colormatch;
import com.example.timur.mainmenu.service.DBservice;

import java.util.Calendar;

/**
 * Created by Timur on 4/19/2016.
 */
public class CardAfterGameActivity extends BaseActivity{
    private TextView txtScore, txtAnotherGame, txtReplay, txtMainMenu;
    DBservice dbService;
    Intent intent;

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
        final int score = intent.getIntExtra("cgScore", -1);
        final int wrongAnswer = intent.getIntExtra("cgTurns", -1);
        Calendar c = Calendar.getInstance();
        final String date = Integer.toString(c.get(Calendar.DATE));
        final Cardgame cg  = new Cardgame(score, wrongAnswer, BaseActivity.USERNAME, date);
        txtScore.setText(Integer.toString(score));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.txtNextGame:
                        intent = new Intent(CardAfterGameActivity.this, GamesScreen.class);
                        new AsyncToDB().execute(cg);
                        break;
                    case R.id.txtMainMenu:
                        intent = new Intent(CardAfterGameActivity.this, MainActivity.class);
                        new AsyncToDB().execute(cg);
                        break;
                    case R.id.txtReplay:
                        intent = new Intent(CardAfterGameActivity.this, CardAfterGameActivity.class);
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

    protected class AsyncToDB extends
            AsyncTask<Cardgame, Void, Void> {

        @Override
        protected Void doInBackground(Cardgame... params) {

            RestAPI api = new RestAPI();
            try {

                api.CreateCardgame(params[0].getUsername(),
                        params[0].getDate(), params[0].getTurns(),
                        params[0].getSpentTime() );

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateColorMatch", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            startActivity(intent);
        }

    }
}

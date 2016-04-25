package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.service.RestAPI;
import com.example.timur.mainmenu.model.Colormatch;
import com.example.timur.mainmenu.service.DBservice;

import java.util.Calendar;

/**
 * Created by Timur on 4/19/2016.
 */
public class ColorMatchAfterGameActivity extends BaseActivity{
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
        final int score = intent.getIntExtra("cmScore", -1);
        final int wrongAnswer = intent.getIntExtra("cmWrongAnswer", -1);
        Calendar c = Calendar.getInstance();
        final String date = Integer.toString(c.get(Calendar.DATE));
        final Colormatch cm  = new Colormatch(score, wrongAnswer, BaseActivity.USERNAME, date);
        txtScore.setText(Integer.toString(score));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.txtNextGame:
                        intent = new Intent(ColorMatchAfterGameActivity.this, GamesScreen.class);
                        new AsyncToDB().execute(cm);
                        break;
                    case R.id.txtMainMenu:
                        intent = new Intent(ColorMatchAfterGameActivity.this, MainActivity.class);
                        new AsyncToDB().execute(cm);
                        break;
                    case R.id.txtReplay:
                        intent = new Intent(ColorMatchAfterGameActivity.this, ColorMatchAfterGameActivity.class);
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
            AsyncTask<Colormatch, Void, Void> {

        @Override
        protected Void doInBackground(Colormatch... params) {

            RestAPI api = new RestAPI();
            try {

                api.CreateColormatch(params[0].getUsername(),
                        params[0].getDate(), params[0].getScore(),
                        params[0].getWrongAnswers() );

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

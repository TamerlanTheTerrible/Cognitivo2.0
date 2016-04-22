package com.example.timur.mainmenu.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.model.Cardgame;
import com.example.timur.mainmenu.model.Raingame;
import com.example.timur.mainmenu.service.DBservice;
import com.example.timur.mainmenu.model.Colormatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Timur on 4/6/2016.
 */
public class LeaderboardActivity extends BaseActivity{
    List<Colormatch> colormatchList;
    DBservice dBservice;
    ListView lvColorMatch;
    ListView lvCardGame;
    ListView lvRaingame;
    final String USER_NAME = "username";
    final String USER_SCORE = "score";
    final String USER_WRONG_ANSWERS = "wrong_answers";
    final String USER_RANKING = "ranking";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_layout);
        dBservice = new DBservice(this);
        dBservice = dBservice.open();
        colormatchList = dBservice.getAllColormatches();
        getColorScores();
        getCardScores();
        getRainScore();
    }


    private void getColorScores(){
        int size = dBservice.getAllColormatches().size();
        if (size>0){
            List<Colormatch> cmList = new ArrayList<>(dBservice.getAllColormatches());
            List<String> usernameList = new ArrayList<>();
            List<Integer> scoreList = new ArrayList<>();
            List<Integer> wrongList = new ArrayList<>();
            for (int i = size-1; i>-1; i--){
                Colormatch cm = cmList.get(i);
                usernameList.add(cm.getUsername());
                scoreList.add(cm.getScore());
                wrongList.add(cm.getWrongAnswers());
            }
            ArrayList<Map<String, Object>> data;
            data = new ArrayList<>(size);
            Map<String, Object> m;
            if(size>5){
                size=5;
            }
            int l=1;
            for(int j=0; j<size; j++){
                m = new HashMap<String, Object>();
                m.put(USER_NAME, usernameList.get(j));
                m.put(USER_SCORE, scoreList.get(j));
                m.put(USER_RANKING, l+".");
                data.add(m);
                l++;
            }
            String[] from ={USER_RANKING, USER_NAME, USER_SCORE};
            int[] to = {R.id.txtCMnumber, R.id.txtCMusername, R.id.txtCMscore};
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item_color_match, from, to);
            lvColorMatch = (ListView)findViewById(R.id.lvColorMatch);
            lvColorMatch.setAdapter(simpleAdapter);
        }
    }

    private void getCardScores(){
        int size = dBservice.getAllCardgames().size();
        if(size>0){
            List<Cardgame> cgList = new ArrayList<>(dBservice.getAllCardgames());
            List<String> usernameList = new ArrayList<>();
            List<Integer> scoreList = new ArrayList<>();
            List<Integer> timeList = new ArrayList<>();
            for(int i=0; i<size; i++){
                Cardgame cg = cgList.get(i);
                usernameList.add(cg.getUsername());
                scoreList.add(cg.getScore());
                timeList.add(cg.getWrongAnswer());
            }
            ArrayList<Map<String, Object>> data;
            data = new ArrayList<>(size);
            Map<String, Object> m;
            if(size>5){
                size=5;
            }
            int l=1;
            for(int j=0; j<size; j++){
                m = new HashMap<String, Object>();
                m.put(USER_NAME, usernameList.get(j));
                m.put(USER_SCORE, timeList.get(j));
                m.put(USER_RANKING, l+".");
                data.add(m);
                l++;
            }
            String[] from ={USER_RANKING, USER_NAME, USER_SCORE};
            int[] to = {R.id.txtCGnumber, R.id.txtCGusername, R.id.txtCGscore};
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item_card_game, from, to);
            lvCardGame = (ListView)findViewById(R.id.lvCardGame);
            lvCardGame.setAdapter(simpleAdapter);
        }
    }

    private void getRainScore(){
        int size = dBservice.getAllRainGames().size();
        if(size>0){
            List<Raingame> cgList = new ArrayList<>(dBservice.getAllRainGames());
            List<String> usernameList = new ArrayList<>();
            List<Integer> scoreList = new ArrayList<>();
            List<Integer> timeList = new ArrayList<>();
            for(int i = size-1; i>-1; i--){
                Raingame cg = cgList.get(i);
                usernameList.add(cg.getUsername());
                scoreList.add(cg.getScore());
                timeList.add(cg.getWrongAnswers());
            }
            ArrayList<Map<String, Object>> data;
            data = new ArrayList<>(size);
            Map<String, Object> m;
            if(size>5){
                size=5;
            }
            int l=1;
            for(int j=0; j<size; j++){
                m = new HashMap<String, Object>();
                m.put(USER_NAME, usernameList.get(j));
                m.put(USER_SCORE, scoreList.get(j));
                m.put(USER_RANKING, l+".");
                data.add(m);
                l++;
            }
            String[] from ={USER_RANKING, USER_NAME, USER_SCORE};
            int[] to = {R.id.txtRGnumber, R.id.txtRGusername, R.id.txtRGscore};
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item_rain_game, from, to);
            lvRaingame = (ListView)findViewById(R.id.lvRainGame);
            lvRaingame.setAdapter(simpleAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dBservice.close();
    }
}

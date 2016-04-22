package com.example.timur.mainmenu.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.timur.mainmenu.JSONParser;
import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.RestAPI;
import com.example.timur.mainmenu.model.Cardgame;
import com.example.timur.mainmenu.model.Colormatch;
import com.example.timur.mainmenu.model.Raingame;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Timur on 4/22/2016.
 */
public class LeaderboardActivity2 extends BaseActivity {
    ListView lvColorMatch, lvCardGame, lvRaingame;
    Context context;
    //ArrayList<String> data;
    //ArrayAdapter<String> adapter;
    ArrayList<Map<String, Object>> rData, cgData, cmData;
    SimpleAdapter rAdapter, cgAdapter, cmAdapter;

    final String USER_NAME = "username";
    final String USER_SCORE = "score";
    final String USER_WRONG_ANSWERS = "wrong_answers";
    final String USER_RANKING = "ranking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_layout);
        context = this;
        Toast.makeText(this, "Loading Please Wait..", Toast.LENGTH_SHORT).show();
        new AsyncLoadRainGameDetails().execute();

    }

    protected class AsyncLoadRainGameDetails extends
            AsyncTask<Void, JSONObject, ArrayList<Raingame>> {
        ArrayList<Raingame> cm = null;

        @Override
        protected ArrayList<Raingame> doInBackground(Void... params) {
            // TODO Auto-generated method stub

            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetDropgameDetails();
                JSONParser parser = new JSONParser();

                cm = parser.parseRaingame(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return cm;
        }

        @Override
        protected void onPostExecute(ArrayList<Raingame> result) {
            // TODO Auto-generated method stub

            int size = result.size();
            int l=1;
            rData = new ArrayList<Map<String, Object>>(size);
            Map<String, Object> m;
            if(size>5){
                size=5;
            }
            for(int j=0; j<size; j++){
                m = new HashMap<String, Object>();
                m.put(USER_NAME, result.get(j).getUsername());
                m.put(USER_SCORE, result.get(j).getScore());
                m.put(USER_RANKING, l+".");
                rData.add(m);
                l++;
            }
            lvRaingame = (ListView) findViewById(R.id.lvRainGame);
            String[] from ={USER_RANKING, USER_NAME, USER_SCORE};
            int[] to = {R.id.txtRGnumber, R.id.txtRGusername, R.id.txtRGscore};
            rAdapter = new SimpleAdapter(getApplicationContext(), rData, R.layout.item_rain_game, from, to);
            lvRaingame.setAdapter(rAdapter);
            //adapter.notifyDataSetChanged();

            new AsyncLoadCardGameDetails().execute();
            Toast.makeText(context,"Loading Completed",Toast.LENGTH_SHORT).show();
        }
    }



    protected class AsyncLoadCardGameDetails extends
            AsyncTask<Void, JSONObject, ArrayList<Cardgame>> {
        ArrayList<Cardgame> cm = null;

        @Override
        protected ArrayList<Cardgame> doInBackground(Void... params) {
            // TODO Auto-generated method stub

            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetCardgameDetails();
                JSONParser parser = new JSONParser();

                cm = parser.parseCardgame(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return cm;
        }

        @Override
        protected void onPostExecute(ArrayList<Cardgame> result) {
            // TODO Auto-generated method stub

            int size = result.size();
            int l=1;
            cgData = new ArrayList<Map<String, Object>>(size);
            Map<String, Object> m;
            if(size>5){
                size=5;
            }
            for(int j=0; j<size; j++){
                m = new HashMap<String, Object>();
                m.put(USER_NAME, result.get(j).getUsername());
                m.put(USER_SCORE, result.get(j).getScore());
                m.put(USER_RANKING, l+".");
                cgData.add(m);
                l++;
            }
            lvCardGame = (ListView) findViewById(R.id.lvCardGame);
            String[] from ={USER_RANKING, USER_NAME, USER_SCORE};
            int[] to = {R.id.txtCGnumber, R.id.txtCGusername, R.id.txtCGscore};
            cgAdapter = new SimpleAdapter(getApplicationContext(), cgData, R.layout.item_card_game, from, to);
            lvCardGame.setAdapter(cgAdapter);
            //adapter.notifyDataSetChanged();

            new AsyncLoadColorMatchDetails().execute();
            Toast.makeText(context,"Loading Completed",Toast.LENGTH_SHORT).show();
        }
    }



    protected class AsyncLoadColorMatchDetails extends
            AsyncTask<Void, JSONObject, ArrayList<Colormatch>> {
        ArrayList<Colormatch> cm = null;

        @Override
        protected ArrayList<Colormatch> doInBackground(Void... params) {
            // TODO Auto-generated method stub

            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetColormatchDetails();
                JSONParser parser = new JSONParser();

                cm = parser.parseColormatch(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return cm;
        }

        @Override
        protected void onPostExecute(ArrayList<Colormatch> result) {
            // TODO Auto-generated method stub

            int size = result.size();
            int l=1;
            cmData = new ArrayList<Map<String, Object>>(size);
            Map<String, Object> m;
            if(size>5){
                size=5;
            }
            for(int j=0; j<size; j++){
                m = new HashMap<String, Object>();
                m.put(USER_NAME, result.get(j).getUsername());
                m.put(USER_SCORE, result.get(j).getScore());
                m.put(USER_RANKING, l+".");
                cmData.add(m);
                l++;
            }
            lvColorMatch = (ListView) findViewById(R.id.lvColorMatch);
            String[] from ={USER_RANKING, USER_NAME, USER_SCORE};
                int[] to = {R.id.txtCMnumber, R.id.txtCMusername, R.id.txtCMscore};
            cmAdapter = new SimpleAdapter(getApplicationContext(), cmData, R.layout.item_color_match, from, to);
            lvColorMatch.setAdapter(cmAdapter);
            //adapter.notifyDataSetChanged();

            Toast.makeText(context,"Loading Completed",Toast.LENGTH_SHORT).show();
        }
    }
}

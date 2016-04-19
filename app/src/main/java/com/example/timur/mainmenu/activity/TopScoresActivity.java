package com.example.timur.mainmenu.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.timur.mainmenu.DeptTable;
import com.example.timur.mainmenu.JSONParser;
import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.RestAPI;
import com.example.timur.mainmenu.model.Colormatch;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Timur on 4/19/2016.
 */
public class TopScoresActivity  extends BaseActivity{
    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    ArrayList<String> data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        // Show the Up button in the action bar.
        //setupActionBar();
        data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.lv_dept);
        context = this;
        adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);
        Toast.makeText(this, "Loading Please Wait..", Toast.LENGTH_SHORT).show();
        new AsyncLoadColorMatchDetails().execute();
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

            for (int i = 0; i < result.size(); i++) {
                data.add(result.get(i).getUsername() + " " + result.get(i).getScore());
            }

            adapter.notifyDataSetChanged();

            Toast.makeText(context,"Loading Completed",Toast.LENGTH_SHORT).show();
        }

    }
}

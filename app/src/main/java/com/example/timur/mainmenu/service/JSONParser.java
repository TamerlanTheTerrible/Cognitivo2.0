package com.example.timur.mainmenu.service;

import android.util.Log;

import com.example.timur.mainmenu.model.Cardgame;
import com.example.timur.mainmenu.model.Colormatch;
import com.example.timur.mainmenu.model.Raingame;
import com.example.timur.mainmenu.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Timur on 4/15/2016.
 */
public class JSONParser {

    public JSONParser()
    {
        super();
    }

    public ArrayList<Colormatch> parseColormatch(JSONObject object){
        ArrayList<Colormatch> cmList = new ArrayList<>();
        try{
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObject = null;
            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                cmList.add((new Colormatch(jsonObject.getInt("Score"), jsonObject.getInt("WrongAnswer"), jsonObject.getString("Username"), jsonObject.getString("Date"))));
            }
        }catch (JSONException e){
            Log.d("JSONParser => parseColormatch", e.getMessage());
        }
        return  cmList;
    }

    public ArrayList<Cardgame> parseCardgame(JSONObject object){
        ArrayList<Cardgame> cmList = new ArrayList<>();
        try{
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObject = null;
            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                cmList.add((new Cardgame(jsonObject.getInt("Score"), jsonObject.getInt("WrongAnswer"), jsonObject.getString("Username"), jsonObject.getString("Date"))));
            }
        }catch (JSONException e){
            Log.d("JSONParser => parseColormatch", e.getMessage());
        }
        return  cmList;
    }

    public ArrayList<Raingame> parseRaingame(JSONObject object){
        ArrayList<Raingame> cmList = new ArrayList<>();
        try{
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObject = null;
            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                cmList.add((new Raingame(jsonObject.getInt("Score"), jsonObject.getInt("WrongAnswer"), jsonObject.getString("Username"), jsonObject.getString("Date"))));
            }
        }catch (JSONException e){
            Log.d("JSONParser => parseColormatch", e.getMessage());
        }
        return  cmList;
    }

    public boolean parseUserAuth(JSONObject object)
    {     boolean userAtuh=false;
        try {
            userAtuh= object.getBoolean("Value");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserAuth", e.getMessage());
        }

        return userAtuh;
    }

    public User parseUser(JSONObject object)
    {
        User userDetail=new User();

        try {
            JSONObject jsonObj=object.getJSONArray("Value").getJSONObject(0);

            userDetail.setFirstname(jsonObj.getString("firstName"));
            userDetail.setLastname(jsonObj.getString("lastName"));
            userDetail.setDOB(jsonObj.getString("dob"));
            userDetail.setEmail(jsonObj.getString("email"));
            userDetail.setCountry(jsonObj.getString("country"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }

        return userDetail;

    }

}

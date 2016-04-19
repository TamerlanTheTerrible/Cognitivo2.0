package com.example.timur.mainmenu;

import android.util.Log;

import com.example.timur.mainmenu.model.Colormatch;

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

    public ArrayList<DeptTable> parseDepartment(JSONObject object)
    {
        ArrayList<DeptTable> arrayList=new ArrayList<DeptTable>();
        try {
            JSONArray jsonArray=object.getJSONArray("Value");
            JSONObject jsonObj=null;
            for(int i=0;i<jsonArray.length();i++)
            {
                jsonObj=jsonArray.getJSONObject(i);
                arrayList.add(new DeptTable(jsonObj.getInt("no"), jsonObj.getString("name")));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;
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

    public UserDetailsTable parseUserDetails(JSONObject object)
    {
        UserDetailsTable userDetail=new UserDetailsTable();

        try {
            JSONObject jsonObj=object.getJSONArray("Value").getJSONObject(0);

            userDetail.setFirstName(jsonObj.getString("firstName"));
            userDetail.setLastName(jsonObj.getString("lastName"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser => parseUserDetails", e.getMessage());
        }

        return userDetail;

    }

}

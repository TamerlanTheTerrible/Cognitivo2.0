package com.example.timur.mainmenu.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.common.Constants;

/**
 * Created by Timur on 2/26/2016.
 */
public class GameSettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // addPreferencesFromResource(R.xml.settings);
        //getWindow().setBackgroundDrawableResource(R.drawable.game_bg);
    }

    public static boolean getVibrate(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Constants.SETTINGS_VIBRATE,
                        Constants.SETTINGS_VIBRATE_DEFAULT);
    }

    /** Get the current value of the music option */
    public static boolean getMusic(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Constants.SETTINGS_MUSIC,
                        Constants.SETTINGS_MUSIC_DEFAULT);
    }

    /** Get the current value user name */
    public static String getUserName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.SETTINGS_USER_NAME,
                        Constants.SETTINGS_USER_NAME_DEFAULT);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}

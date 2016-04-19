package com.example.timur.mainmenu.activity;

import com.example.timur.mainmenu.activity.GameSettingsActivity;
/*import com.example.timur.mainmenu.LocalHighScoreActivity;
import com.example.timur.mainmenu.activity.NewGameSelectionActivity;
import com.example.timur.mainmenu.audio.AudioPlayer;*/
import com.example.timur.mainmenu.common.Common;
import com.example.timur.mainmenu.common.Constants;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.timur.mainmenu.common.Common;

/**
 * Created by Timur on 2/26/2016.
 */
public class BaseActivity extends AppCompatActivity {
    public Common common = new Common();
    public Vibrator vibrator;
    public static String USERNAME="user1";
    public static boolean LOGGED_IN = false;
    private String password;
    private boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

     @Override
   public boolean onOptionsItemSelected(MenuItem item) {
        AudioPlayer.play(this, R.raw.button);

        switch (item.getItemId()) {
            case R.id.menu_new_game:
                startActivity(new Intent(this, NewGameSelectionActivity.class));
                return true;
            case R.id.menu_settings:
                Intent settingsActivityIntent = new Intent(this,
                        GameSettingsActivity.class);
                startActivity(settingsActivityIntent);
                return true;
            case R.id.menu_high_score:
                startActivity(new Intent(this, LocalHighScoreActivity.class));
            case R.id.menu_about:
                Dialog aboutDialog = new AboutDialog(this);
                aboutDialog.show();

        }

        return false;
    }

    public long getVibrationIntensity() {
        if (!GameSettingsActivity.getVibrate(this.getApplicationContext())) {
            return new Long(0);
        } else
            return Constants.VIBRATE_ITENSITY;
    }*/
}

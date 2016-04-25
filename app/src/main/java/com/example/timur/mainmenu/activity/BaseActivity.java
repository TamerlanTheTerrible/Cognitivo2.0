package com.example.timur.mainmenu.activity;

import com.example.timur.mainmenu.R;
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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.timur.mainmenu.common.Common;

/**
 * Created by Timur on 2/26/2016.
 */
public class BaseActivity extends AppCompatActivity {
    public Vibrator vibrator;
    public static String USERNAME="user1";
    public static boolean LOGGED_IN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public static void playCorrectAnswer(Context context){
        MediaPlayer mpCorrect = MediaPlayer.create(context, R.raw.correct_answer);
        mpCorrect.start();
    }

    public static void  playWrongAnswer(Context context){
        MediaPlayer  mpWrong = MediaPlayer.create(context, R.raw.wrong_answer);
        mpWrong.start();
    }
}

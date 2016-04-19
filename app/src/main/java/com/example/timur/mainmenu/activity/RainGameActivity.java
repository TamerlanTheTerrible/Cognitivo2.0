package com.example.timur.mainmenu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.timur.mainmenu.R;

import java.util.Random;

/**
 * Created by Timur on 2/29/2016.
 */
public class RainGameActivity extends Activity {

    Animation dropDownAnim;
    Animation fadeIn;
    TextView dropImage;
    private View view;
    String[] drops = new String[10];
    DisplayMetrics displayMetrics;
    Random r;
    int width;
    AbsoluteLayout.LayoutParams absParams;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rain_game_layout);

        dropImage = (TextView)findViewById(R.id.dropView);
        dropDownAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_down);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_quick);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;


        raining();

    }

    void newDrop(TextView dropView){
        absParams = (AbsoluteLayout.LayoutParams)dropView.getLayoutParams();
        r = new Random();
        absParams.x = r.nextInt(width);
        absParams.y = -300;
        if(absParams.x>900){
           absParams.x = absParams.x-100;
        }
        dropView.setText(Integer.toString(absParams.x));
        dropView.setLayoutParams(absParams);
        //dropView.setVisibility(View.VISIBLE);
        dropView.startAnimation(dropDownAnim);
    }

    void raining(){

        int sec = 2000;
        for(int i = 0; i<3; i++){

            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    newDrop3();

                }
            }, sec);
        }
    }

    String problems(){
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();
        String str = x + "+" +y;
        return str;
    }

    void problemsToDrops(){
        for (int i=0; i<drops.length; i++){
            drops[i] = problems();
        }
    }

    void newDrop2(){
        ViewGroup parent = (ViewGroup)findViewById(R.id.sky);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.drop_layout, null);
        TextView textView = (TextView)findViewById(R.id.dropView);
        parent.addView(view);
    }

    void newDrop3(){
             newDrop2();
             Random r = new Random(1000);
             int num = r.nextInt();
             TranslateAnimation animation = new TranslateAnimation(num, num, -200, 1700);
             animation.setDuration(8000);
             animation.setFillAfter(false);
             animation.setAnimationListener(new DropDownAnimListener());
             view.startAnimation(animation);
    }

    public class DropDownAnimListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            view.clearAnimation();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(view.getWidth(), view.getHeight());
            lp.setMargins(0,0,0,0);
            view.setLayoutParams(lp);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
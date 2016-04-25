package com.example.timur.mainmenu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Timur on 07/01/2016.
 */
public class ColorMatchActivity extends Activity {

    TextView scoreView;
    TextView meaningView;
    TextView colorView;
    Button noButton;
    Button yesButton;
    TextView timeView;
    ImageView toastImage;

    String currentColor;
    String currentMeaning;

    Context context;
    private ArrayList<String> colors = new ArrayList<String>();

    Toast toast;

    public ColorMatchActivity(){
        colors.add("black");
        colors.add("blue");
        colors.add("red");
    }

    private int score=0;
    private int wrongAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.color_match2);

        context = getApplicationContext();

        scoreView = (TextView)findViewById(R.id.scoreView);
        timeView = (TextView) findViewById(R.id.timeView);
        meaningView = (TextView)findViewById(R.id.meaningView);
        colorView = (TextView)findViewById(R.id.colorView);
        noButton = (Button)findViewById(R.id.noButton);
        yesButton = (Button)findViewById(R.id.yesButton);

        toast = new Toast(context);
        newMeaning();
        newColor();


        new CountDownTimer(15000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished>9000){
                    timeView.setText("time: 00:"+Long.toString(millisUntilFinished/1000));
                }else {
                    timeView.setText("time: 00:0"+Long.toString(millisUntilFinished/1000));
                }


            }
            @Override
            public void onFinish() {
                timeView.setText("time: 00:00");
                callResultDialog();
                noButton.setEnabled(false);
                yesButton.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(4000);
                            Intent intent = new Intent(ColorMatchActivity.this, ColorMatchAfterGameActivity.class);
                            intent.putExtra("cmScore", score);
                            intent.putExtra("cmWrongAnswer", wrongAnswer);
                            startActivity(intent);
                            finish();
                        }catch (Exception e){
                            e.fillInStackTrace();
                        }
                    }
                }).start();
            }
        }.start();

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.cancel();
                answerCheck(noButton.getId(), currentMeaning, currentColor);
                newMeaning();
                newColor();

            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.cancel();
                answerCheck(yesButton.getId(), currentMeaning, currentColor);
                newMeaning();
                newColor();

            }
        });
    }

    void newMeaning(){
        Random rand = new Random();
        int n = rand.nextInt(colors.size());
        int m = rand.nextInt(colors.size());
        meaningView.setText(colors.get(n));
        currentMeaning=(String)meaningView.getText();
        colorView.setText(colors.get(m));
    }

    void newColor(){
        Random rand = new Random();
        String col;
        int n = rand.nextInt(colors.size());
        int m = rand.nextInt(colors.size());
        switch (n){
            case 0:
                meaningView.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                meaningView.setTextColor(getResources().getColor(R.color.blue));
                break;
            case 2:
                meaningView.setTextColor(getResources().getColor(R.color.red));
                break;
        }

        switch (m){
            case 0:
                colorView.setTextColor(getResources().getColor(R.color.black));
                currentColor = "black";
                break;
            case 1:
                colorView.setTextColor(getResources().getColor(R.color.blue));
                currentColor = "blue";
                break;
            case 2:
                colorView.setTextColor(getResources().getColor(R.color.red));
                currentColor = "red";
                break;
        }
    }

    //checks if the answer is correct
    void answerCheck(int id, String meaning, String col){
        String str;
        boolean answer;

        if (id == noButton.getId()){
            if (col.equals(meaning)){
                answer=false;
            }
            else {
                answer=true;

            }
        }

        else {
            if (col.equals(meaning)){
                answer=true;
            }
            else {
                answer=false;
            }
        }

        //if the answer is correct score increases and tick appears
        if(answer){
            yesToast();
            BaseActivity.playCorrectAnswer(context);
            score++;
            scoreView.setText("score: "+Integer.toString(score));
        }else {
            noToast();
            BaseActivity.playWrongAnswer(context);
            wrongAnswer++;
        }
    }

    //custom Toast for correct answer
    void yesToast(){
        toast = new Toast(context);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_correct_wrong_layout, (ViewGroup)findViewById(R.id.custom_dialog));
        toastImage = (ImageView)layout.findViewById(R.id.toastImage);
        toastImage.setImageResource(R.drawable.correct);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0, 400);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    //custom Toast for wrong answer
    void noToast(){
        toast = new Toast(context);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_correct_wrong_layout, (ViewGroup)findViewById(R.id.custom_dialog));
        toastImage = (ImageView)layout.findViewById(R.id.toastImage);
        toastImage.setImageResource(R.drawable.wrong);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0, 400);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    void callResultDialog(){
        Dialog resultDialog = new Dialog(ColorMatchActivity.this);
        resultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        resultDialog.setContentView(getLayoutInflater().inflate(R.layout.result_dialog, (ViewGroup)findViewById(R.id.resultDialogContainer)));
        TextView resultText = (TextView)resultDialog.findViewById(R.id.resultDialogText);
        String result = score+" correct answers!";
        resultText.setText(result);
        resultDialog.show();
    }

    @Override
    public void onBackPressed() {
        // do nothing.
    }
}

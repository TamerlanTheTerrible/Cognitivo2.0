package com.example.timur.mainmenu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.model.Drop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Timur on 3/4/2016.
 */
public class RainGameActivity extends Activity {
    ViewGroup parent;
    TextView txtScore, txtTime, view;
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, btnClear, btnAnswer;
    EditText edtAnswer;

    List<Drop> dropList;
    List<String> problemList;
    Handler handler;
    private int mInterval = 2000;
    int lives, score, answer, wrongAnswer;
    final public String TAG = "My Tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rain_game_layout);

        dropList = new ArrayList<Drop>();
        problemList = new ArrayList<String>();

        score = 0;
        wrongAnswer=0;

        txtScore = (TextView)findViewById(R.id.score);
        txtScore.setText(Integer.toString(score));
        txtTime = (TextView)findViewById(R.id.time);
        handler = new Handler();
        parent = (RelativeLayout)findViewById(R.id.sky);
        edtAnswer = (EditText)findViewById(R.id.edtAnswer);

        btnOne = (Button)findViewById(R.id.one);
        btnTwo = (Button)findViewById(R.id.two);
        btnThree = (Button)findViewById(R.id.three);
        btnFour = (Button)findViewById(R.id.four);
        btnFive = (Button)findViewById(R.id.five);
        btnSix = (Button)findViewById(R.id.six);
        btnSeven = (Button)findViewById(R.id.seven);
        btnEight = (Button)findViewById(R.id.eight);
        btnNine = (Button)findViewById(R.id.nine);
        btnZero = (Button)findViewById(R.id.zero);
        btnAnswer = (Button)findViewById(R.id.btnAnswer);
        btnClear = (Button)findViewById(R.id.btnClear);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.one:
                        setEdtAnswerText("1");
                        break;
                    case R.id.two:
                        setEdtAnswerText("2");
                        break;
                    case R.id.three:
                        setEdtAnswerText("3");
                        break;
                    case R.id.four:
                        setEdtAnswerText("4");
                        break;
                    case R.id.five:
                        setEdtAnswerText("5");
                        break;
                    case R.id.six:
                        setEdtAnswerText("6");
                        break;
                    case R.id.seven:
                        setEdtAnswerText("7");
                        break;
                    case R.id.eight:
                        setEdtAnswerText("8");
                        break;
                    case R.id.nine:
                        setEdtAnswerText("9");
                        break;
                    case R.id.zero:
                        setEdtAnswerText("0");
                        break;
                    case R.id.btnClear:
                        edtAnswer.setText("");
                        answer = 0;
                        break;
                    case R.id.btnAnswer:
                        checkAnswer();

                        //dropList.remove(1);
                        break;
                }
            }
        };

        btnOne.setOnClickListener(onClickListener);
        btnTwo.setOnClickListener(onClickListener);
        btnThree.setOnClickListener(onClickListener);
        btnFour.setOnClickListener(onClickListener);
        btnFive.setOnClickListener(onClickListener);
        btnSix.setOnClickListener(onClickListener);
        btnSeven.setOnClickListener(onClickListener);
        btnEight.setOnClickListener(onClickListener);
        btnNine.setOnClickListener(onClickListener);
        btnZero.setOnClickListener(onClickListener);
        btnAnswer.setOnClickListener(onClickListener);
        btnClear.setOnClickListener(onClickListener);

        startRepeatingTask();

        new CountDownTimer(10000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished>10000){
                    txtTime.setText("time: 00:"+Long.toString(millisUntilFinished/1000));
                }else {
                    txtTime.setText("time: 00:0"+Long.toString(millisUntilFinished/1000));
                }
            }
            @Override
            public void onFinish() {
                txtTime.setText("time: 00:00");
                //callResultDialog();
                btnAnswer.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(2000);
                            Intent intent = new Intent(RainGameActivity.this, RainAfterGameActivity.class);
                            intent.putExtra("rgScore", score);
                            intent.putExtra("rgWrongAnswer", wrongAnswer);
                            startActivity(intent);
                            finish();

                        }catch (Exception e){
                            e.fillInStackTrace();
                        }
                    }
                }).start();
            }
        }.start();
    }

    void setEdtAnswerText(String str){
        String initialText = edtAnswer.getText().toString();
        edtAnswer.setText(initialText+str);
    }

    void checkAnswer(){
        answer = Integer.parseInt(edtAnswer.getText().toString());
        edtAnswer.setText("");
        int count=parent.getChildCount();
        for (int i=0; i< count; i++){
            if (answer == dropList.get(i).getAnswer()){
                score++;
                txtScore.setText("score: "+Integer.toString(score));
                TextView currentView=dropList.get(i).getView();
                currentView.clearAnimation();
                ((RelativeLayout) currentView.getParent()).removeView(currentView);
                dropList.remove(i);
                break;
            }else {
                wrongAnswer++;
            }
        }
    }

     private TextView newDrop(){
        Random r = new Random();
        int a = r.nextInt(10);
        int b = r.nextInt(10);
        TextView view = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.drop_layout, parent, false);
        view.setText(a+"+"+b);
        Drop newDrop = new Drop();
        newDrop.setView(view);
        newDrop.setA(a);
        newDrop.setB(b);
        parent.addView(view);
        dropList.add(newDrop);

        return  view;

    }

    Runnable raining = new Runnable() {
        @Override
        public void run() {
            try {
                TextView textView= newDrop();
                Random r = new Random();
                int num = r.nextInt(900);
                TranslateAnimation animation = new TranslateAnimation(num, num, -200, 1600);
                animation.setDuration(12000);
                animation.setFillAfter(true);
                animation.setAnimationListener(new DropDownAnimListener());

                textView.startAnimation(animation);
            } finally {
                handler.postDelayed(raining, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        raining.run();
    }

    void stopRepeatingTask() {
        //handler.removeCallbacks(raining);
    }

    void removeAllDrops(){
//        int childcount = parent.getChildCount();
//        for (int i=0; i < parent.getChildCount(); i++){
//            TextView currentView=dropList.get(i).getView();
//            currentView.clearAnimation();
//            dropList.remove(i);
//        }
        parent.clearAnimation();
        parent.removeAllViews();

    }

    void removeDrop(Drop drop){
        //drop.getView().clearAnimation();
        parent.removeView(drop.getView());
        dropList.remove(drop);
    }

/*    void callResultDialog(){
        Dialog resultDialog = new Dialog(RainDropGame.this);
        resultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        resultDialog.setContentView(getLayoutInflater().inflate(R.layout.result_dialog, (ViewGroup)findViewById(R.id.resultDialogContainer)));
        TextView resultText = (TextView)resultDialog.findViewById(R.id.resultDialogText);
        String result = score+" correct answers!";
        resultText.setText(result);
        resultDialog.show();
    }*/

    public class DropDownAnimListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

             //stopRepeatingTask();
            //removeAllDrops();
            //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(view.getWidth(), view.getHeight());
            //lp.setMargins(0,0,0,0);
            //view.setLayoutParams(lp);
        }

        @Override
        public void onAnimationRepeat(Animation animation){

        }
    }
}

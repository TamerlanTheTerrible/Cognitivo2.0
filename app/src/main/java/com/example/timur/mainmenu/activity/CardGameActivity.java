package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.animation.ChangeViewBackground;
import com.example.timur.mainmenu.animation.Flip3dAnimation;
import com.example.timur.mainmenu.common.Common;
import com.example.timur.mainmenu.common.Constants;
//import com.example.timur.mainmenu.dialog.CardGameGameOverDialog;
import com.example.timur.mainmenu.model.Card;
import com.example.timur.mainmenu.model.GameTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Timur on 2/26/2016.
 */
public class CardGameActivity extends BaseActivity {

    public List<GameTable> gameTables;
    public Common common = new Common();
    public Vibrator vibrator;
    Toast toast;
    private ImageView newGameButton;
    private ImageView localHighScoreButton;
    private static Object lock = new Object();
    private ButtonListener buttonListener;
    private static int TABLE_ROW_COUNT = -1;
    private static int TABLE_COL_COUNT = -1;
    private int[][] gameTable;
    private List<Drawable> images;
    private Drawable backImage;
    private TableLayout mainTable;
    private Card firstCard;
    private Card secondCard;
    int turns;
    int correctGuess;
    private UpdateCardsHandler handler;

    //Timer
    private Chronometer chronometer;
    long elapsedTime;
    String currentTime = "";
    long startTime = SystemClock.elapsedRealtime();

    private TextView turnsCaption;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_game_layout);
        gameTables = common.getCardGameTables();
        turnsCaption = ((TextView)findViewById(R.id.turns_caption));
        images = common.loadImages(this);
        backImage = common.getBackImage(this);
        buttonListener = new ButtonListener();
        handler = new UpdateCardsHandler();
        chronometer = (Chronometer)findViewById(R.id.timmer);
        /*newGameButton = (ImageView)findViewById(R.id.game_new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewCardGameDialog();
            }
        });*/
        /**localHighScoreButton = (ImageView) findViewById(R.id.game_local_highscore_button);
        localHighScoreButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(CardGameActivity.this,
                        CardGameLocalHighScoreActivity.class);
                startActivity(intent);
            }
        });*/

        newGame(getIntent().getIntExtra(Constants.GAME_TABLE_ROW,
                Constants.DEFAULT_GAME_TABLE_SIZE_ROW), getIntent()
                .getIntExtra(Constants.GAME_TABLE_COL,
                        Constants.DEFAULT_GAME_TABLE_SIZE_COL));

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            public void onChronometerTick(Chronometer arg0) {
                elapsedTime = elapsedTime + 1000;
                long minutes = common.getMinutesValue(elapsedTime);
                long seconds = common.getSecondsValue(elapsedTime);
                currentTime = common.timeToString(minutes, seconds);
                arg0.setText(currentTime);
                if (Constants.MAX_GAME_TIME_MIN < minutes) {
                    stopGame();
                }
            }
        });
    }

    protected void stopGame() {
        this.finish();
    }

    public void updateTurnsCaption() {
        turnsCaption
                .setText("Turns: " + correctGuess);
    }

    public void newGame(int row, int col) {
        //newGameButton.setVisibility(View.GONE);
        //localHighScoreButton.setVisibility(View.GONE);
        elapsedTime = 0;
        chronometer.setText("00:00");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        TABLE_COL_COUNT = col;
        TABLE_ROW_COUNT = row;
        gameTable = new int[TABLE_COL_COUNT][TABLE_ROW_COUNT];
        mainTable = (TableLayout) findViewById(R.id.game_table);
        mainTable.setVisibility(View.VISIBLE);
        for (int y = 0; y < TABLE_ROW_COUNT; y++) {
            mainTable.addView(createRow(y));
        }
        correctGuess = 0;
        turns = 0;
        updateTurnsCaption();
        firstCard = null;
        secondCard = null;
        loadCards();
    }

    public void gameOver() {
        //Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CardGameActivity.this, CardAfterGameActivity.class);
        intent.putExtra("cgScore", correctGuess);
        intent.putExtra("cgTurns", turns);
        //Toast.makeText(getApplicationContext(), correctGuess+" "+currentTime, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
        //localHighScoreButton.setVisibility(View.VISIBLE);

    }

    private TableRow createRow(int y) {
        TableRow row = new TableRow(mainTable.getContext());
        row.setHorizontalGravity(Gravity.CENTER);
        for (int x = 0; x < TABLE_COL_COUNT; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        ImageView button = new ImageView(mainTable.getContext());
        button.setBackgroundDrawable(backImage);
        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener);
        button.setAdjustViewBounds(true);
        return button;
    }

    private void loadCards() {
        try {
            int size = TABLE_ROW_COUNT * TABLE_COL_COUNT;
            Random r = new Random();

            Log.i("loadCards()", "size=" + size);

            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                list.add(new Integer(i));
            }
            for (int i = size - 1; i >= 0; i--) {
                int t = 0;
                if (i > 0) {
                    t = r.nextInt(i);
                }
                t = list.remove(t).intValue();
                gameTable[i % TABLE_COL_COUNT][i / TABLE_COL_COUNT] = t
                        % (size / 2);

                Log.i("loadCards()", "card[" + (i % TABLE_COL_COUNT) + "]["
                        + (i / TABLE_COL_COUNT) + "]="
                        + gameTable[i % TABLE_COL_COUNT][i / TABLE_COL_COUNT]);
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }

    }

    private void applyRotation(ImageView currentView, Drawable imageToReplace,
                               float start, float end) {
        // Find the center of image
        final float centerX = currentView.getWidth() / 2.0f;
        final float centerY = currentView.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
                centerX, centerY);
        rotation.setDuration(Constants.FLIP_DURATION_TIME);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new ChangeViewBackground(currentView,
                imageToReplace));
        currentView.startAnimation(rotation);

    }

    private void hideOnRotation(ImageView currentView, Drawable imageToReplace,
                                float start, float end) {
        // Find the center of image
        final float centerX = currentView.getWidth() / 2.0f;
        final float centerY = currentView.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Flip3dAnimation rotation = new Flip3dAnimation(start, end,
                centerX, centerY);
        rotation.setDuration(Constants.FLIP_DURATION_TIME);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        currentView.startAnimation(rotation);

    }

    class ButtonListener implements View.OnClickListener {

        public void onClick(View v) {

            synchronized (lock) {
                if (firstCard != null && secondCard != null) {
                    return;
                }
                int id = v.getId();
                int x = id / 100;
                int y = id % 100;
                turnCard((ImageView) v, x, y);
            }

        }

        private void turnCard(ImageView button, int x, int y) {
            if (firstCard == null) {
                firstCard = new Card(button, x, y);
                applyRotation(button, images.get(gameTable[x][y]), 0, 90);
            } else {
                if (firstCard.getX() == x && firstCard.getY() == y) {
                    return; // the user pressed the same card
                }
                applyRotation(button, images.get(gameTable[x][y]), 0, 90);
                secondCard = new Card(button, x, y);
                turns++;
                updateTurnsCaption();
                TimerTask tt = new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };

                Timer t = new Timer(false);
                t.schedule(tt, Constants.TIMMER_TASK_DELAY);
            }
        }
    }

/*    public void openNewCardGameDialog() {
        ArrayList<String> captions = new ArrayList<String>();
        for (GameTable gt : gameTables) {
            captions.add(gt.toString());
        }
        new AlertDialog.Builder(this).setTitle(R.string.select_grid_size_title)
                .setItems(captions.toArray(new CharSequence[captions.size()]),
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialoginterface, int i) {
                                startCardGame(i);
                            }
                        }).show();
    }*/

/*    public void startCardGame(int selected) {
        Intent viewActivityIntent = new Intent(this, CardGameActivity.class);
        viewActivityIntent.putExtra(Constants.GAME_TABLE_COL, gameTables.get(
                selected).getColumns());
        viewActivityIntent.putExtra(Constants.GAME_TABLE_ROW, gameTables.get(
                selected).getRows());
        startActivity(viewActivityIntent);
    }*/

    class UpdateCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();
            }
        }

        public void checkCards() {
            if (gameTable[secondCard.getX()][secondCard.getY()] == gameTable[firstCard
                    .getX()][firstCard.getY()]) {
                secondCard.getButton().setClickable(false);
                firstCard.getButton().setClickable(false);
                hideOnRotation(secondCard.getButton(), backImage, 0, 90);
                hideOnRotation(firstCard.getButton(), backImage, 0, 90);
                correctGuess++;
                //vibrator.vibrate(getVibrationIntensity());
            } else {
                applyRotation(secondCard.getButton(), backImage, 0, 90);
                applyRotation(firstCard.getButton(), backImage, 0, 90);
            }
            firstCard = null;
            secondCard = null;
            if (correctGuess == 8) {
                gameOver();
            }
        }

        public long getVibrationIntensity() {
            if (!GameSettingsActivity.getVibrate(getApplicationContext())) {
                return new Long(0);
            } else
                return Constants.VIBRATE_ITENSITY;
        }
    }
}


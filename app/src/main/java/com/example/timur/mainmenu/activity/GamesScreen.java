package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.timur.mainmenu.R;


public class GamesScreen extends BaseActivity {

    ImageView flexImage;
    ImageView memoryImage;
    ImageView solvingImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout);

        flexImage = (ImageView)findViewById(R.id.flexibilityImg);
        flexImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamesScreen.this, ColorMatchPreGame.class));
            }
        });

        memoryImage = (ImageView)findViewById(R.id.memoryImg);
        memoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamesScreen.this, CardPreGameActivity.class));
            }
        });

        solvingImage = (ImageView)findViewById(R.id.solvingImg);
        solvingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamesScreen.this, RainPreGameActivity.class));
            }
        });
    }

}

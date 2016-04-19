package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.timur.mainmenu.R;

/**
 * Created by Timur on 07/01/2016.
 */
public class CardPreGameActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_pregame_layout);

        final TextView btnBegin = (TextView)findViewById(R.id.flexBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardPreGameActivity.this, CardGameActivity.class));
                finish();
            }
        });
    }
}

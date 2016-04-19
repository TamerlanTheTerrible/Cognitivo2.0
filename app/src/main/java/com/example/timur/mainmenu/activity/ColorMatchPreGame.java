package com.example.timur.mainmenu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.timur.mainmenu.R;

/**
 * Created by Timur on 07/01/2016.
 */
public class ColorMatchPreGame extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flex_pregame_layout);

        final TextView btnBegin = (TextView)findViewById(R.id.flexBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColorMatchPreGame.this, ColorMatchActivity.class));
                finish();
            }
        });
    }
}

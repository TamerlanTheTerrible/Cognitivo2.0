package com.example.timur.mainmenu.common;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.timur.mainmenu.R;
import com.example.timur.mainmenu.model.GameTable;
import com.example.timur.mainmenu.memory.ImageHolder;


public class Common {

    public List<Drawable> loadImages(Context context) {
        List<Drawable> images = new ArrayList<Drawable>();
        images.add(context.getResources().getDrawable(R.drawable.s0));
        images.add(context.getResources().getDrawable(R.drawable.s1));
        images.add(context.getResources().getDrawable(R.drawable.s2));
        images.add(context.getResources().getDrawable(R.drawable.s3));
        images.add(context.getResources().getDrawable(R.drawable.s4));
        images.add(context.getResources().getDrawable(R.drawable.s5));
        images.add(context.getResources().getDrawable(R.drawable.s6));
        images.add(context.getResources().getDrawable(R.drawable.s7));
        images.add(context.getResources().getDrawable(R.drawable.s8));
        images.add(context.getResources().getDrawable(R.drawable.s9));
        images.add(context.getResources().getDrawable(R.drawable.s10));
        images.add(context.getResources().getDrawable(R.drawable.s11));
        Collections.shuffle(images);
        return images;
    }

    public List<ImageHolder> loadImagesToImageHolder(Context context) {
        List<ImageHolder> images = new ArrayList<ImageHolder>();
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s0), context.getResources().getDrawable(
                R.drawable.s0)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s1), context.getResources().getDrawable(
                R.drawable.s1)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s2), context.getResources().getDrawable(
                R.drawable.s2)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s3), context.getResources().getDrawable(
                R.drawable.s3)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s4), context.getResources().getDrawable(
                R.drawable.s4)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s5), context.getResources().getDrawable(
                R.drawable.s5)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s6), context.getResources().getDrawable(
                R.drawable.s6)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s7), context.getResources().getDrawable(
                R.drawable.s7)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s8), context.getResources().getDrawable(
                R.drawable.s8)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s9), context.getResources().getDrawable(
                R.drawable.s9)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s10), context.getResources().getDrawable(
                R.drawable.s10)));
        images.add(new ImageHolder(context.getResources().getDrawable(
                R.drawable.s11), context.getResources().getDrawable(
                R.drawable.s11)));
        Collections.shuffle(images);
        return images;
    }

    public Drawable getBackImage(Context context) {
        return context.getResources().getDrawable(R.drawable.back);
    }

    public List<GameTable> getCardGameTables() {
        List<GameTable> gameTables = new ArrayList<GameTable>();
        gameTables.add(createCardGameTable(2, 2));
        gameTables.add(createCardGameTable(3, 2));
        gameTables.add(createCardGameTable(4, 3));
        gameTables.add(createCardGameTable(4, 4));
        gameTables.add(createCardGameTable(4, 5));
        gameTables.add(createCardGameTable(4, 6));

        return gameTables;
    }

    public List<String> getCardGameTableIDs() {
        List<String> ids = new ArrayList<String>();
        for (GameTable gt : getCardGameTables()) {
            ids.add(generateTableId(gt.getColumns(), gt.getRows()));
        }
        return ids;
    }

    public String generateTableId(int col, int row) {
        return col + "x" + row;
    }

    protected GameTable createCardGameTable(int col, int row) {
        return new GameTable(col, row);
    }

    public long getMinutesValue(long elapsedTime) {
        return ((elapsedTime) / 1000) / 60;
    }

    public long getSecondsValue(long elapsedTime) {
        return ((elapsedTime) / 1000) % 60;
    }

    public String timeToString(long minutes, long seconds) {
        return (minutes < 10 ? "0" + minutes : minutes) + ":"
                + (seconds < 10 ? "0" + seconds : seconds);
    }

    public String timeToString(long time) {
        return timeToString(getMinutesValue(time), getSecondsValue(time));
    }


}

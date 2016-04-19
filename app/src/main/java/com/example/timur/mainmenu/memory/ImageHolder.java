package com.example.timur.mainmenu.memory;

import android.graphics.drawable.Drawable;

/**
 * Created by Timur on 2/26/2016.
 */
public class ImageHolder {
    private Drawable smallImage;
    private Drawable bigImage;

    public ImageHolder(Drawable smallImage, Drawable bigImage) {
        super();
        this.smallImage = smallImage;
        this.bigImage = bigImage;
    }

    public Drawable getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(Drawable smallImage) {
        this.smallImage = smallImage;
    }

    public Drawable getBigImage() {
        return bigImage;
    }

    public void setBigImage(Drawable bigImage) {
        this.bigImage = bigImage;
    }

}

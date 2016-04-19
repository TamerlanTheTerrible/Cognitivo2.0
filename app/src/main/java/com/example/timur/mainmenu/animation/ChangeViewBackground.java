package com.example.timur.mainmenu.animation;

import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by Timur on 2/26/2016.
 */
public class ChangeViewBackground implements Animation.AnimationListener {
    private ImageView currentView;
    private Drawable imageToReplace;

    public ChangeViewBackground(ImageView currentView, Drawable imageToReplace) {
        this.currentView = currentView;
        this.imageToReplace = imageToReplace;
    }

    public void onAnimationEnd(Animation animation) {
        currentView.post(new SwapBackgroundImages(currentView, imageToReplace));

    }

    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}


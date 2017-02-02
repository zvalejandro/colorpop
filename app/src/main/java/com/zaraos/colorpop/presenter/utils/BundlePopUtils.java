package com.zaraos.colorpop.presenter.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zaraos.colorpop.model.PopInformer;
import com.zaraos.colorpop.model.constants.POPAPI;

/**
 * Created by Alex on 25/01/17.
 */

public class BundlePopUtils {

    private int circleColor = 0;
    private int rectColor = 0;
    private boolean isBehindStatusBar = false;
    private int statusBarHeight = 0;
    private int startPointX = 0;
    private int startPointY = 0;


    public BundlePopUtils(Bundle arguments) {
        PopInformer informer = arguments.getParcelable(POPAPI.POP_INFORMER);

        if (informer != null) {
            if (informer.getCircleColor() != null) {
                circleColor = informer.getCircleColor();
            }
            if(informer.getStartPointX() != null && informer.getStartPointY() != null){
                startPointX = informer.getStartPointX();
                startPointY = informer.getStartPointY();
            }
            if(informer.getPageColor() != null){
                rectColor = informer.getPageColor();
            }
            if(informer.getStatusBarHeight() != null){
                statusBarHeight = informer.getStatusBarHeight();
            }

            isBehindStatusBar = informer.isBehindStatusBar();
        }

        /*

        if (arguments.containsKey(POPAPI.POP_CIRCLE_COLOR)) {
            circleColor = arguments.getInt(POPAPI.POP_CIRCLE_COLOR);
        }
        if (arguments.containsKey(POPAPI.POP_POINT_X_)
                && arguments.containsKey(POPAPI.POP_POINT_Y)) {
            startPointX = arguments.getInt(POPAPI.POP_POINT_X_);
            startPointY = arguments.getInt(POPAPI.POP_POINT_Y);
        }
        if (arguments.containsKey(POPAPI.POP_RECT_COLOR)) {
            rectColor = arguments.getInt(POPAPI.POP_RECT_COLOR);
        }
        if (arguments.containsKey(POPAPI.POP_BEHIND_STATUSBAR)) {
            isBehindStatusBar = arguments.getBoolean(POPAPI.POP_BEHIND_STATUSBAR);
        }
        if (arguments.containsKey(POPAPI.POP_STATUSBAR_HEIGHT)) {
            statusBarHeight = arguments.getInt(POPAPI.POP_STATUSBAR_HEIGHT);
        }*/
    }

    /**
     * @return Int | the color of circle
     */
    public int getCircleColor() {
        return circleColor;
    }

    /**
     * @return Int | the page color that grows from bottom
     */
    public int getPageColor() {
        return rectColor;
    }

    /**
     * @return Boolean | is views behind status bar or not
     */
    public boolean getIsBehindStatusbar() {
        return isBehindStatusBar;
    }

    /**
     * @return Int | start point x of circles animation
     */
    public int getStartPointX() {
        return startPointX;
    }

    /**
     * @return Int | start point y of circles animation
     */
    public int getStartPointY() {
        return startPointY;
    }

    /**
     * @return Int | height of status bar
     */
    public int getStatusBarHeight() {
        return statusBarHeight;
    }


    public static class Builder {

        private int circleColor;
        private int pageColor;
        private boolean isBehindStatusBar = false;
        private int statusBarHeight = 0;
        private int startPointX = 0;
        private int startPointY = 0;
        private PopInformer informer = new PopInformer();

        public Builder(Context context) {
            statusBarHeight = ColorPopUtils.getStatusBarHeightPixels(context);
            informer.setStatusBarHeight(statusBarHeight);
        }

        public static Builder init(Context context) {
            return new Builder(context);
        }

        /**
         * @param color the color of circle animation
         */
        public Builder setCircleColor(int color) {
            informer.setCircleColor(color);
            circleColor = color;
            return this;
        }

        /**
         * @param color the color of the page that grows from bottom
         */
        public Builder setPageColor(int color) {
            informer.setPageColor(color);
            pageColor = color;
            return this;

        }

        /**
         * @param view the base view that is used for determining the start point of
         *             the circles animation
         * @param mode determines the position of start point of animation on the
         *             base view
         *             <p>
         *             isBehindStatusBar if your view's are behind status bar set this to true used in
         *             API +19 </br> Note : if the start point of animation is not
         *             correct then change this boolean and see if it's correct or
         *             not
         */
        public Builder setBaseView(final View view, int mode) {
            PopInformer informer = new PopInformer();
            informer.setView(view);
            return setBaseView(informer, mode);
        }

        public Builder setBaseView(final PopInformer informer, int mode) {
            this.isBehindStatusBar = (android.os.Build.VERSION.SDK_INT >= 19);
            int width = informer.getWidth();
            int height = informer.getHeight();
            int windowLeft = informer.getWindowLeft();
            int windowTop = informer.getWindowTop();
            if (mode == POPAPI.POP_MODE_LEFT_EDGE) {
                startPointX = windowLeft + width / 15;
            } else if (mode == POPAPI.POP_MODE_CENTER) {
                startPointX = windowLeft + (width / 2);
            } else if (mode == POPAPI.POP_MODE_RIGHT_EDGE) {
                startPointX = windowLeft + (width - (width / 15));
            }
            startPointY = windowTop + (height / 2);
            if (!this.isBehindStatusBar) {
                startPointY -= statusBarHeight;
            }

            this.informer.setWidth(width);
            this.informer.setHeight(height);
            this.informer.setWindowLeft(windowLeft);
            this.informer.setWindowTop(windowTop);
            this.informer.setStartPointX(startPointX);
            this.informer.setStartPointY(startPointY);
            this.informer.setBehindStatusBar(isBehindStatusBar);

            return this;
        }

        public Builder informFragment(Fragment fragment) {
            Bundle arguments = new Bundle();
            //arguments.putInt(POPAPI.POP_POINT_X_, startPointX);
            //arguments.putInt(POPAPI.POP_POINT_Y, startPointY);
            arguments.putParcelable(POPAPI.POP_INFORMER, informer);
            fragment.setArguments(arguments);
            return this;
        }

        public Builder informActivity(Intent intent) {
            //intent.putExtra(POPAPI.POP_POINT_X_, startPointX);
            //intent.putExtra(POPAPI.POP_POINT_Y, startPointY);
            intent.putExtra(POPAPI.POP_INFORMER, informer);
            return this;
        }

        public Builder informColorPopFragment(Fragment fragment) {
            Bundle arguments = new Bundle();
            //arguments.putInt(POPAPI.POP_CIRCLE_COLOR, circleColor);
            //arguments.putInt(POPAPI.POP_POINT_X_, startPointX);
            //arguments.putInt(POPAPI.POP_POINT_Y, startPointY);
            arguments.putParcelable(POPAPI.POP_INFORMER, informer);
            fragment.setArguments(arguments);
            return this;
        }

        public Builder informColorPopIntent(Intent intent) {
            //intent.putExtra(POPAPI.POP_CIRCLE_COLOR, circleColor);
            //intent.putExtra(POPAPI.POP_POINT_X_, startPointX);
            //intent.putExtra(POPAPI.POP_POINT_Y, startPointY);
            intent.putExtra(POPAPI.POP_INFORMER, informer);
            return this;
        }

        /**
         * @param fragment it will give the fragment these informations </br>
         *                 <p>
         *                 #1 Int | the circles color </br> #2 Int | the page color </br>
         *                 #3 Int | start point of the animation </br> #4 Boolean | is
         *                 views behind status bar or not </br>
         */
        public Builder informColorPopPageFragment(Fragment fragment) {
            Bundle arguments = new Bundle();
            //arguments.putInt(POPAPI.POP_CIRCLE_COLOR, circleColor);
            //arguments.putInt(POPAPI.POP_RECT_COLOR, pageColor);
            //arguments.putInt(POPAPI.POP_POINT_X_, startPointX);
            //arguments.putInt(POPAPI.POP_POINT_Y, startPointY);
            arguments.putBoolean(POPAPI.POP_BEHIND_STATUSBAR, isBehindStatusBar);
            arguments.putInt(POPAPI.POP_STATUSBAR_HEIGHT, statusBarHeight);
            arguments.putParcelable(POPAPI.POP_INFORMER, informer);
            fragment.setArguments(arguments);
            return this;
        }

        public Builder informColorPopPageActivity(Intent intent) {
            //intent.putExtra(POPAPI.POP_CIRCLE_COLOR, circleColor);
            //intent.putExtra(POPAPI.POP_RECT_COLOR, pageColor);
            //intent.putExtra(POPAPI.POP_POINT_X_, startPointX);
            //intent.putExtra(POPAPI.POP_POINT_Y, startPointY);
            intent.putExtra(POPAPI.POP_BEHIND_STATUSBAR, isBehindStatusBar);
            intent.putExtra(POPAPI.POP_STATUSBAR_HEIGHT, statusBarHeight);
            intent.putExtra(POPAPI.POP_INFORMER, informer);
            return this;
        }

    }
}
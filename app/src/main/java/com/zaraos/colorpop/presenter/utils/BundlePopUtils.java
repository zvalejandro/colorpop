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
            if (informer.getStartPointX() != null && informer.getStartPointY() != null) {
                startPointX = informer.getStartPointX();
                startPointY = informer.getStartPointY();
            }
            if (informer.getPageColor() != null) {
                rectColor = informer.getPageColor();
            }
            if (informer.getStatusBarHeight() != null) {
                statusBarHeight = informer.getStatusBarHeight();
            }

            isBehindStatusBar = informer.isBehindStatusBar();
        }
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

        private PopInformer informer = new PopInformer();

        public Builder(Context context) {
            informer.setStatusBarHeight(ColorPopUtils.getStatusBarHeightPixels());
        }

        public static Builder init(Context context) {
            return new Builder(context);
        }

        /**
         * @param color the color of circle animation
         */
        public Builder setCircleColor(int color) {
            informer.setCircleColor(color);
            return this;
        }

        /**
         * @param color the color of the page that grows from bottom
         */
        public Builder setPageColor(int color) {
            informer.setPageColor(color);
            return this;

        }

        /**
         * @param view the base view that is used for determining the start point of
         *             the circles animation
         * @param mode determines the position of start point of animation on the base view
         */
        public Builder setBaseView(View view, int mode) {
            return setBaseView(new PopInformer(view), mode);
        }

        /**
         * isBehindStatusBar if your view's are behind status bar set this to true
         * used in API +19 </br> Note : if the start point of animation is not
         * correct then change this boolean and see if it's correct or not
         **/
        public Builder setBaseView(final PopInformer dimens, int mode) {
            int startPointX = 0;
            int startPointY = 0;
            informer.setBehindStatusBar(android.os.Build.VERSION.SDK_INT >= 19);
            informer.setWidth(dimens.getWidth());
            informer.setHeight(dimens.getHeight());
            informer.setWindowLeft(dimens.getWindowLeft());
            informer.setWindowTop(dimens.getWindowTop());

            if (mode == POPAPI.POP_MODE_LEFT_EDGE) {
                startPointX = informer.getWindowLeft() + informer.getWidth() / 15;
            } else if (mode == POPAPI.POP_MODE_CENTER) {
                startPointX = informer.getWindowLeft() + (informer.getWidth() / 2);
            } else if (mode == POPAPI.POP_MODE_RIGHT_EDGE) {
                startPointX = informer.getWindowLeft()
                        + (informer.getWidth() - (informer.getWidth() / 15));
            }
            startPointY = informer.getWindowTop() + (informer.getHeight() / 2);
            if (!informer.isBehindStatusBar())
                startPointY -= informer.getStatusBarHeight();

            informer.setStartPointX(startPointX);
            informer.setStartPointY(startPointY);

            return this;
        }

        public Builder informFragment(Fragment fragment) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(POPAPI.POP_INFORMER, informer);
            fragment.setArguments(arguments);
            return this;
        }

        public Builder informActivity(Intent intent) {
            intent.putExtra(POPAPI.POP_INFORMER, informer);
            return this;
        }
    }
}
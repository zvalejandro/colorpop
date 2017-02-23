package com.zaraos.colorpop.presenter.utils;

import android.content.res.Resources;

import com.zaraos.colorpop.Application;

/**
 * Created by Alex on 25/01/17.
 */

public class DimensUtils {

    /**
     * @return status bar height in pixels
     */
    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = Application.getInstance().getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = Application.getInstance()
                    .getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}

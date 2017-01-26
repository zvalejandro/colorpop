package com.zaraos.colorpop.presenter.utils;

import android.content.Context;

/**
 * Created by Alex on 25/01/17.
 */

public class ColorPopUtils {
    /**
     *
     * @param context
     *
     * @return status bar height pixels
     */
    public static int getStatusBarHeightPixels(Context context) {
        int status_bar_height = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            status_bar_height = context.getResources().getDimensionPixelSize(
                    resourceId);
        }
        return status_bar_height;
    }
}

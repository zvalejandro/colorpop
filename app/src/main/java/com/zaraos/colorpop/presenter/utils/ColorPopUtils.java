package com.zaraos.colorpop.presenter.utils;

import com.zaraos.colorpop.Application;

/**
 * Created by Alex on 25/01/17.
 */

public class ColorPopUtils {

    public static int getStatusBarHeightPixels() {
        int status_bar_height = 0;
        int resourceId = Application.getInstance().getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            status_bar_height = Application.getInstance().getResources().getDimensionPixelSize(
                    resourceId);
        }
        return status_bar_height;
    }
}

package com.zaraos.colorpop.presenter.utils;

import android.content.res.Resources;

/**
 * Created by Alex on 01/02/17.
 */

public class ConvertUtils {

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}

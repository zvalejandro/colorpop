package com.zaraos.colorpop.presenter.utils;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.zaraos.colorpop.Application;

/**
 * Created by Alex on 26/01/17.
 */

public class ColorUtils {

    public static int get(@ColorRes int id) {
        return ContextCompat.getColor(Application.getInstance(), id);
    }

}

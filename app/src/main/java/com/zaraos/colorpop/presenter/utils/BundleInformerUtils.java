package com.zaraos.colorpop.presenter.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Alex on 25/01/17.
 */

public class BundleInformerUtils {
    public static final String CIRCLE_COLOR_KEY = "circle_color";
    public static final String START_POINT_X_KEY = "start_point_x";
    public static final String START_POINT_Y_KEY = "start_point_y";
    public static final String RECT_COLOR_KEY = "rect_color";
    public static final String IS_VIEWS_BEHIND_STATUS_BAR = "is_behind_statusbar";
    public static final String STATUSBAR_HEIGHT = "statusbar_height";
    public static final int MODE_LEFT_EDGE = 0;
    public static final int MODE_CENTER = 1;
    public static final int MODE_RIGHT_EDGE = 2;
    private int circle_color;
    private int page_color;
    private boolean is_behind_statusbar = false;
    private int statusbar_height = 0;
    private int start_point_x = 0;
    private int start_point_y = 0;

    public BundleInformerUtils(Context context) {
        statusbar_height = ColorPopUtils.getStatusBarHeightPixels(context);
    }

    public static BundleInformerUtils init(Context context) {
        BundleInformerUtils bundleInformerUtils = new BundleInformerUtils(context);
        bundleInformerUtils.statusbar_height = ColorPopUtils.getStatusBarHeightPixels(context);
        return bundleInformerUtils;
    }

    /**
     * @param color the color of circle animation
     */
    public BundleInformerUtils setCircleColor(int color) {
        circle_color = color;
        return this;

    }

    /**
     * @param color the color of the page that grows from bottom
     */
    public BundleInformerUtils setPageColor(int color) {
        page_color = color;
        return this;

    }

    /**
     * @param view                      the base view that is used for determining the start point of
     *                                  the circles animation
     * @param mode                      determines the position of start point of animation on the
     *                                  base view </br> {@code FragmentInformer.MODE_CENTER} </br>
     *                                  {@code FragmentInformer.MODE_LEFT_EDGE} </br>
     *                                  {@code FragmentInformer.MODE_RIGHT_EDGE}
     * @param is_views_behind_statusbar if your view's are behind status bar set this to true used in
     *                                  API +19 </br> Note : if the start point of animation is not
     *                                  correct then change this boolean and see if it's correct or
     *                                  not
     */
    public BundleInformerUtils setBaseView(final View view, int mode,
                                           boolean is_views_behind_statusbar) {
        is_behind_statusbar = is_views_behind_statusbar;
        int width = view.getWidth();
        int height = view.getHeight();
        int[] layout_location = {0, 0};
        view.getLocationOnScreen(layout_location);
        if (mode == MODE_LEFT_EDGE) {
            start_point_x = layout_location[0] + width / 15;
        } else if (mode == MODE_CENTER) {
            start_point_x = layout_location[0] + (width / 2);
        } else if (mode == MODE_RIGHT_EDGE) {
            start_point_x = layout_location[0] + (width - (width / 15));
        }
        start_point_y = layout_location[1] + (height / 2);
        if (!is_behind_statusbar) {
            start_point_y -= statusbar_height;
        }
        return this;
    }

    /**
     * @param fragment it will give the fragment these informations </br> #1 Int |
     *                 start point of the animation
     */
    public BundleInformerUtils informFragment(Fragment fragment) {
        Bundle arguments = new Bundle();
        arguments.putInt(START_POINT_X_KEY, start_point_x);
        arguments.putInt(START_POINT_Y_KEY, start_point_y);
        fragment.setArguments(arguments);
        return this;
    }

    public BundleInformerUtils informActivity(Intent intent) {
        intent.putExtra(START_POINT_X_KEY, start_point_x);
        intent.putExtra(START_POINT_Y_KEY, start_point_y);
        return this;
    }

    /**
     * @param fragment it will give the fragment these informations </br> #1 Int |
     *                 the circles color </br> #2 Int | start point of the animation
     */
    public BundleInformerUtils informColorPopFragment(Fragment fragment) {
        Bundle arguments = new Bundle();
        arguments.putInt(CIRCLE_COLOR_KEY, circle_color);
        arguments.putInt(START_POINT_X_KEY, start_point_x);
        arguments.putInt(START_POINT_Y_KEY, start_point_y);
        fragment.setArguments(arguments);
        return this;
    }

    public BundleInformerUtils informColorPopIntent(Intent intent) {
        intent.putExtra(CIRCLE_COLOR_KEY, circle_color);
        intent.putExtra(START_POINT_X_KEY, start_point_x);
        intent.putExtra(START_POINT_Y_KEY, start_point_y);
        return this;
    }

    /**
     * @param fragment it will give the fragment these informations </br>
     *                 <p>
     *                 #1 Int | the circles color </br> #2 Int | the page color </br>
     *                 #3 Int | start point of the animation </br> #4 Boolean | is
     *                 views behind status bar or not </br>
     */
    public BundleInformerUtils informColorPopPageFragment(Fragment fragment) {
        Bundle arguments = new Bundle();
        arguments.putInt(CIRCLE_COLOR_KEY, circle_color);
        arguments.putInt(RECT_COLOR_KEY, page_color);
        arguments.putInt(START_POINT_X_KEY, start_point_x);
        arguments.putInt(START_POINT_Y_KEY, start_point_y);
        arguments.putBoolean(IS_VIEWS_BEHIND_STATUS_BAR, is_behind_statusbar);
        arguments.putInt(STATUSBAR_HEIGHT, statusbar_height);
        fragment.setArguments(arguments);
        return this;
    }

    public BundleInformerUtils informColorPopPageActivity(Intent intent) {
        intent.putExtra(CIRCLE_COLOR_KEY, circle_color);
        intent.putExtra(RECT_COLOR_KEY, page_color);
        intent.putExtra(START_POINT_X_KEY, start_point_x);
        intent.putExtra(START_POINT_Y_KEY, start_point_y);
        intent.putExtra(IS_VIEWS_BEHIND_STATUS_BAR, is_behind_statusbar);
        intent.putExtra(STATUSBAR_HEIGHT, statusbar_height);
        return this;
    }

}
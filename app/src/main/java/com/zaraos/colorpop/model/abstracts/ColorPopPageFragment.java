package com.zaraos.colorpop.model.abstracts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.zaraos.colorpop.presenter.utils.BundlePopUtils;

/**
 * Created by Alex on 25/01/17.
 */

public abstract class ColorPopPageFragment extends ColorPopFragment {

    private boolean is_behind_statusbar = false;
    private int statusbar_height = 0;
    protected int header_height = 0;

    public ColorPopPageFragment() {
        should_animate = false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            popView.setHaveRectAnimation(true);
            Bundle arguments = getArguments();
            BundlePopUtils info_parser = new BundlePopUtils(
                    arguments);
            popView.setRectColor(info_parser.getPageColor());
            is_behind_statusbar = info_parser.getIsBehindStatusbar();
            statusbar_height = info_parser.getStatusBarHeight();
            popView.setAnimationListener(this);
            // use a OnGlobalLayoutListener to measure the header view's
            // location and height
            if (haveHeader()) {
                final View headerView = getHeaderView(view);
                ViewTreeObserver viewTreeObserver = headerView
                        .getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver
                            .addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                                @SuppressLint("NewApi")
                                @Override
                                public void onGlobalLayout() {
                                    if (android.os.Build.VERSION.SDK_INT >= 16) {
                                        headerView.getViewTreeObserver()
                                                .removeOnGlobalLayoutListener(
                                                        this);
                                    } else {
                                        headerView.getViewTreeObserver()
                                                .removeGlobalOnLayoutListener(
                                                        this);
                                    }
                                    int[] layout_location = { 0, 0 };
                                    headerView
                                            .getLocationOnScreen(layout_location);
                                    header_height += layout_location[1];
                                    if (!is_behind_statusbar) {
                                        header_height -= statusbar_height;
                                    }
                                    header_height += headerView.getHeight();
                                    popView
                                            .setRectSpaceTop(header_height);
                                    popView.animatePop();
                                }
                            });
                }
            } else {
                popView.setRectSpaceTop(header_height);
                popView.animatePop();
            }
        }
    }

    /**
     * if set to true then you should set the header view to
     * {@code getHeaderView()}
     */
    public abstract boolean haveHeader();

    /**
     * the header view must be set here
     *
     * @param fragmentView
     *            the base view of fragment
     */
    public abstract View getHeaderView(View fragmentView);

}
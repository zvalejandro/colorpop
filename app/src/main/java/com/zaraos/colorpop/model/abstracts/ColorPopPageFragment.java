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

    private boolean isBehindStatusBar = false;
    private int statusBarHeight = 0;
    protected int headerHeight = 0;

    public ColorPopPageFragment() {
        shouldAnimate = false;
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
            isBehindStatusBar = info_parser.getIsBehindStatusbar();
            statusBarHeight = info_parser.getStatusBarHeight();
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
                                    int[] layout_location = {0, 0};
                                    headerView
                                            .getLocationOnScreen(layout_location);
                                    headerHeight += layout_location[1];
                                    if (!isBehindStatusBar) {
                                        headerHeight -= statusBarHeight;
                                    }
                                    headerHeight += headerView.getHeight();
                                    popView
                                            .setRectSpaceTop(headerHeight);
                                    popView.animatePop();
                                }
                            });
                }
            } else {
                popView.setRectSpaceTop(headerHeight);
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
     * @param fragmentView the base view of fragment
     */
    public abstract View getHeaderView(View fragmentView);

}
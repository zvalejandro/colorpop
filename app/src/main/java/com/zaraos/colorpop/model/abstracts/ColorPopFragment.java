package com.zaraos.colorpop.model.abstracts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.zaraos.colorpop.presenter.utils.BundlePopUtils;
import com.zaraos.colorpop.view.widgets.PopBackgroundView;

/**
 * Created by Alex on 25/01/17.
 */

public abstract class ColorPopFragment extends Fragment implements PopBackgroundView.AnimationListener {

    protected boolean shouldAnimate = true;
    protected FrameLayout container;
    protected PopBackgroundView popView;
    private int circleColor;
    protected View rootPopView;

    public ColorPopFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        container = new FrameLayout(getContext());
        container.setLayoutParams(lp);
        popView = new PopBackgroundView(getContext());
        popView.setLayoutParams(lp);
        rootPopView = onCreateFragmentView(inflater, container, savedInstanceState);
        rootPopView.setVisibility(View.INVISIBLE);
        container.addView(popView, 0);
        container.addView(rootPopView, 1);
        return container;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            Bundle arguments = getArguments();
            BundlePopUtils args = new BundlePopUtils(arguments);
            circleColor = args.getCircleColor();
            popView.setCircleColor(circleColor);
            popView.setCircleStartPointCoordinates(
                    args.getStartPointX(), args.getStartPointY());
            if (shouldAnimate) {
                popView.setAnimationListener(this);
                popView.animatePop();
            }
        }
    }

    /**
     * @return the circles animation colorF color
     */
    public int getCircleColor() {
        return circleColor;
    }

    /**
     * @return returns the {@link PopBackgroundView} used for aniamtions
     */
    public PopBackgroundView getPopBackgroundView() {
        return popView;
    }

    @Override
    public void onAnimationEnd() {
        onBackgroundAnimationEnd();
    }

    @Override
    public void setArguments(Bundle args) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle.putAll(args);
        } else {
            super.setArguments(args);
        }
    }

    /**
     * you should create your views here instead of {@code onViewCreated}
     */
    public abstract View onCreateFragmentView(LayoutInflater inflater,
                                              ViewGroup container, Bundle savedInstanceState);

    /**
     * called when the background animations ends
     */
    public abstract void onBackgroundAnimationEnd();
}
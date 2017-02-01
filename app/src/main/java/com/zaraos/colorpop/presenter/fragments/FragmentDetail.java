package com.zaraos.colorpop.presenter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zaraos.colorpop.R;
import com.zaraos.colorpop.model.abstracts.ColorPopFragment;
import com.zaraos.colorpop.presenter.utils.ColorPopUtils;
import com.zaraos.colorpop.view.utilsview.ToolbarUtilsView;

/**
 * Created by Alex on 31/01/17.
 */

public class FragmentDetail extends ColorPopFragment {

    public static FragmentDetail newInstance(Bundle args) {
        FragmentDetail fragmentEmbargo = new FragmentDetail();
        fragmentEmbargo.setArguments(args);
        fragmentEmbargo.setHasOptionsMenu(true);
        return fragmentEmbargo;
    }

    private View rootView;
    private ToolbarUtilsView toolbar;
    private View content;

    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            rootView.setPadding(0, ColorPopUtils.getStatusBarHeightPixels(getContext()), 0, 0);
        }

        toolbar = new ToolbarUtilsView();
        toolbar.with((AppCompatActivity) getActivity(), rootView);
        content = rootView.findViewById(R.id.detail_view_content);
        return rootView;
    }

    @Override
    public void onBackgroundAnimationEnd() {
        rootView.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.INVISIBLE);

        Context context = getContext();
        if (context != null) {
            Animation grow = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom);
            grow.setAnimationListener(onAnimationListener());
            content.startAnimation(grow);
            content.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setHomeIndicatorBack("Detalle");
    }

    private Animation.AnimationListener onAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Context context = getContext();
                if (context != null) {
                    Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                    toolbar.setVisibility(fade_in, View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }
}

package com.zaraos.colorpop.presenter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zaraos.colorpop.R;
import com.zaraos.colorpop.model.abstracts.ColorPopFragment;
import com.zaraos.colorpop.model.abstracts.ColorPopPageFragment;

/**
 * Created by Alex on 31/01/17.
 */

public class FragmentDetail extends ColorPopPageFragment {

    public static FragmentDetail newInstance() {
        FragmentDetail fragmentEmbargo = new FragmentDetail();
        fragmentEmbargo.setArguments(null);
        fragmentEmbargo.setHasOptionsMenu(true);
        return fragmentEmbargo;
    }

    private View rootView;

    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onBackgroundAnimationEnd() {
        Context context = getContext();
        if (context != null) {
            Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            rootView.startAnimation(fade_in);
            rootView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean haveHeader() {
        return false;
    }

    @Override
    public View getHeaderView(View fragmentView) {
        return null;
    }
}

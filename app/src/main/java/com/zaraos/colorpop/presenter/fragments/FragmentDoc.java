package com.zaraos.colorpop.presenter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zaraos.colorpop.presenter.utils.ColorPopUtils;
import com.zaraos.colorpop.R;
import com.zaraos.colorpop.model.abstracts.ColorPopPageFragment;
import com.zaraos.colorpop.presenter.utils.ColorUtils;

/**
 * Created by Alex on 25/01/17.
 */

public class FragmentDoc extends ColorPopPageFragment {

    public static FragmentDoc newInstance(Bundle args) {
        FragmentDoc fragmentDoc = new FragmentDoc();
        fragmentDoc.setArguments(null);
        fragmentDoc.setHasOptionsMenu(true);
        return fragmentDoc;
    }

    View rootView;

    @Override
    public View onCreateFragmentView(LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_doc, container, false);
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            rootView.setBackgroundColor(ColorUtils.get(R.color.blue_grey_800));
            rootView.setPadding(0, ColorPopUtils.getStatusBarHeightPixels(getContext()), 0, 0);
        }
        return rootView;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    public View getHeaderView(View fragmentView) {
        return fragmentView.findViewById(R.id.header_view);
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
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("AndroidColorPop");
    }
}
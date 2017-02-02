package com.zaraos.colorpop.presenter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ListView;

import com.zaraos.colorpop.presenter.utils.ColorPopUtils;
import com.zaraos.colorpop.R;
import com.zaraos.colorpop.view.adapters.ListItemAdapter;
import com.zaraos.colorpop.model.abstracts.ColorPopFragment;

/**
 * Created by Alex on 26/01/17.
 */

public class FragmentList extends ColorPopFragment implements AnimationListener {

    public static FragmentList newInstance(Bundle args) {
        FragmentList fragmentList = new FragmentList();
        fragmentList.setArguments(null);
        fragmentList.setHasOptionsMenu(true);
        return fragmentList;
    }

    private View rootView;
    private View headerView;
    private ListView list;
    private Toolbar toolbar;

    @Override
    public View onCreateFragmentView(LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        if (android.os.Build.VERSION.SDK_INT >= 19)
            rootView.setPadding(0, ColorPopUtils.getStatusBarHeightPixels(), 0, 0);

        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        list = (ListView) rootView.findViewById(R.id.list);
        return rootView;
    }

    @Override
    public void onBackgroundAnimationEnd() {
        rootView.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.INVISIBLE);
        Context context = getContext();
        if (context != null) {
            Animation grow = AnimationUtils.loadAnimation(getContext(),
                    R.anim.slide_in_bottom);
            grow.setAnimationListener(this);
            list.startAnimation(grow);
            list.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("AndroidColorPop");
        headerView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_list_header, list, false);
        headerView.setVisibility(View.INVISIBLE);
        list.addHeaderView(headerView);
        ListItemAdapter list_adapter = new ListItemAdapter(getContext());
        list.setAdapter(list_adapter);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Context context = getContext();
        if (context != null) {
            Animation fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            toolbar.startAnimation(fade_in);
            headerView.startAnimation(fade_in);
            toolbar.setVisibility(View.VISIBLE);
            headerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
package com.zaraos.colorpop.presenter.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaraos.colorpop.presenter.utils.ColorPopUtils;
import com.zaraos.colorpop.presenter.utils.BundlePopUtils;
import com.zaraos.colorpop.view.widgets.PopBackgroundView;
import com.zaraos.colorpop.R;

/**
 * Created by Alex on 25/01/17.
 */

public class FragmentCover extends Fragment {

    public static FragmentCover newInstance(Bundle args) {
        FragmentCover fragmentCover = new FragmentCover();
        fragmentCover.setArguments(null);
        fragmentCover.setHasOptionsMenu(true);
        return fragmentCover;
    }

    private PopBackgroundView popView;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cover, container, false);
        if (android.os.Build.VERSION.SDK_INT >= 19)
            rootView.setPadding(0, ColorPopUtils.getStatusBarHeightPixels(), 0, 0);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popView = (PopBackgroundView) view.findViewById(R.id.pop_background);
        popView.setCircleColor(Color.BLUE);

        Bundle arguments = getArguments();
        BundlePopUtils args = new BundlePopUtils(arguments);
        popView.setCircleStartPointCoordinates(args.getStartPointX(),
                args.getStartPointY());
        popView.setCirclesFillType(PopBackgroundView.CIRLCES_FILL_WIDTH_TYPE);
        popView.animatePop();
    }
}
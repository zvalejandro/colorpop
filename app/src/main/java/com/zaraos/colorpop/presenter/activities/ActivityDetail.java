package com.zaraos.colorpop.presenter.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zaraos.colorpop.R;
import com.zaraos.colorpop.model.constants.POPAPI;
import com.zaraos.colorpop.presenter.fragments.FragmentDetail;
import com.zaraos.colorpop.presenter.fragments.FragmentHome;
import com.zaraos.colorpop.presenter.fragments.FragmentList;
import com.zaraos.colorpop.presenter.utils.BundlePopUtils;
import com.zaraos.colorpop.presenter.utils.ColorUtils;
import com.zaraos.colorpop.view.utilsview.ToolbarUtilsView;

/**
 * Created by Alex on 31/01/17.
 */

public class ActivityDetail extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_empty);
        //initFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();

        fab = (FloatingActionButton) findViewById(R.id.floating_button);
        fab.setOnClickListener(getOnClickListener());
        fab.performClick();
        //initFragment();
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initFragment(v);
            }
        };
    }

    private void initFragment(View v) {
        boolean isViewBehindStatusBar = false;
        if (android.os.Build.VERSION.SDK_INT >= 19)
            isViewBehindStatusBar = true;

        Fragment fragment = FragmentDetail.newInstance(null);
        BundlePopUtils.Builder.init(this)
                .setCircleColor(ColorUtils.get(R.color.blue_grey_800))
                .setPageColor(Color.WHITE)
                .setBaseView(v, POPAPI.POP_MODE_CENTER, isViewBehindStatusBar)
                .informColorPopPageFragment(fragment);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                //.addToBackStack(null)
                //.replace(android.R.id.content, fragment)
                .replace(android.R.id.content, fragment)
                .commit();
    }

}
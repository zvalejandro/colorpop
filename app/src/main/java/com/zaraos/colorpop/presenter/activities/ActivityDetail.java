package com.zaraos.colorpop.presenter.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zaraos.colorpop.R;
import com.zaraos.colorpop.presenter.fragments.FragmentDetail;
import com.zaraos.colorpop.view.utilsview.ToolbarUtilsView;

/**
 * Created by Alex on 31/01/17.
 */

public class ActivityDetail extends AppCompatActivity {

    private ToolbarUtilsView toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_drawer);
        //initToolbar();
        initFragment();
    }

    private void initToolbar() {
        toolbar = new ToolbarUtilsView();
        toolbar.with(this);
        toolbar.setHomeIndicatorBack("Detalle");
        toolbar.hideElevation();
        toolbar.setVisibility(View.INVISIBLE);
    }

    public ToolbarUtilsView getToolbar() {
        return toolbar;
    }

    public AppBarLayout getBar(){
        return toolbar.getAppBarLayout();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                //.addToBackStack(null)
                .replace(android.R.id.content, FragmentDetail.newInstance(getIntent().getExtras()))
                .commit();
    }

}
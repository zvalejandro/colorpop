package com.zaraos.colorpop.view.utilsview;

import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zaraos.colorpop.R;

/**
 * Created by Alex on 31/01/17.
 */

public class ToolbarUtilsView {

    private AppCompatActivity activity;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private AppBarLayout appBarLayout;

    private AppBarLayout getAppBarLayout() {
        if (appBarLayout == null)
            appBarLayout = (AppBarLayout) activity.findViewById(R.id.appbarlayout);
        return appBarLayout;
    }

    protected Toolbar getToolbar() {
        if (toolbar == null)
            toolbar = (Toolbar) activity.findViewById(R.id.toolbar);

        showElevation();
        return toolbar;
    }

    protected DrawerLayout getDrawerLayout() {
        if (drawer == null)
            drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        return drawer;
    }

    protected ActionBarDrawerToggle getActionBarDrawerToggle() {
        if (toggle == null)
            toggle = new ActionBarDrawerToggle(activity, getDrawerLayout(), toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        return toggle;
    }

    private ImageView getImageElevation() {
        return ((ImageView) activity.findViewById(R.id.toolbar_elevation));
    }

    public void showElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getAppBarLayout().setElevation(5f);
            //getImageElevation().setVisibility(View.GONE);
            getImageElevation().setVisibility(View.VISIBLE);
        } else {
            getImageElevation().setVisibility(View.VISIBLE);
        }
    }

    public void hideElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getAppBarLayout().setElevation(0);
            getImageElevation().setVisibility(View.GONE);
        } else {
            getImageElevation().setVisibility(View.GONE);
        }
    }

    public void with(AppCompatActivity activity) {
        this.activity = activity;
        activity.setSupportActionBar(getToolbar());
        try {
            //noinspection ConstantConditions
            activity.getSupportActionBar().setTitle("");
            getDrawerLayout().addDrawerListener(getActionBarDrawerToggle());
            getActionBarDrawerToggle().syncState();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setToolbarBackgroundColor(@ColorRes int idColor) {
        getToolbar().setBackgroundColor(getColor(idColor));
    }

    public void setToolbarTitle(String title) {
        getToolbar().setTitle(title);
    }

    public void setToolbarTitleTextColor(@ColorRes int id) {
        getToolbar().setTitleTextColor(getColor(id));
    }

    private int getColor(int id) {
        return ContextCompat.getColor(activity, id);
    }

    public void setHomeIndicatorBack(String title) {
        setHomeIndicator(title, R.mipmap.ico_toolbar_back_black);
    }

    public void setHomeIndicatorClose(String title) {
        setHomeIndicator(title, R.mipmap.ico_toolbar_back_black);
    }

    public void setStatusBarColor(@ColorRes int idColor) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getColor(idColor));
        }
    }

    private void setHomeIndicator(String title, @DrawableRes int icon) {
        getToolbar().setTitle(title);
        getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        getActionBarDrawerToggle().setHomeAsUpIndicator(icon);
        getActionBarDrawerToggle().setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.onBackPressed();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
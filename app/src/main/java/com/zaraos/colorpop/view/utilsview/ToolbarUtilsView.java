package com.zaraos.colorpop.view.utilsview;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.zaraos.colorpop.R;

/**
 * Created by Alex on 31/01/17.
 */

@SuppressWarnings("ConstantConditions")
public class ToolbarUtilsView {

    private AppCompatActivity activity;
    private View rootView;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;

    public AppBarLayout getAppBarLayout() {
        if (appBarLayout == null)
            appBarLayout = (AppBarLayout) rootView.findViewById(R.id.appbarlayout);
        return appBarLayout;
    }

    protected Toolbar getToolbar() {
        if (toolbar == null)
            toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        return toolbar;
    }

    private ImageView getImageElevation() {
        return ((ImageView) rootView.findViewById(R.id.toolbar_elevation));
    }

    public void showElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getAppBarLayout().setElevation(5f);
            getImageElevation().setVisibility(View.GONE);
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
        with(activity, activity.getWindow().getDecorView().findViewById(android.R.id.content));
    }

    public void with(AppCompatActivity activity, View rootView) {
        this.activity = activity;
        this.rootView = rootView;
        activity.setSupportActionBar(getToolbar());
        try {
            activity.getSupportActionBar().setTitle("");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String title) {
        getToolbar().setTitle(title);
    }

    public void setTitleTextColor(@ColorRes int id) {
        getToolbar().setTitleTextColor(getColor(id));
    }

    public void setBackgroundColor(@ColorRes int idColor) {
        getToolbar().setBackgroundColor(getColor(idColor));
    }

    public void setVisibility(int visibility) {
        getToolbar().setVisibility(visibility);
    }

    public void setVisibility(Animation animation, int visibility) {
        getToolbar().startAnimation(animation);
        getToolbar().setVisibility(visibility);
        getImageElevation().startAnimation(animation);
        getImageElevation().setVisibility(visibility);
    }

    private int getColor(int id) {
        return ContextCompat.getColor(activity, id);
    }

    public void setStatusBarColor(@ColorRes int idColor) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getColor(idColor));
        }
    }

    public void setHomeIndicatorClose(String title) {
        setHomeIndicator(title, R.mipmap.ico_toolbar_back_black);
    }

    public void setHomeIndicatorBack(String title) {
        setHomeIndicatorBackLight(title);
    }

    public void setHomeIndicatorBackLight(String title) {
        setHomeIndicator(title, R.mipmap.ico_toolbar_back_white);
    }

    public void setHomeIndicatorBackDark(String title) {
        setHomeIndicator(title, R.mipmap.ico_toolbar_back_black);
    }

    private void setHomeIndicator(String title, @DrawableRes int icon) {
        getToolbar().setTitle(title);
        getToolbar().setNavigationIcon(icon);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
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

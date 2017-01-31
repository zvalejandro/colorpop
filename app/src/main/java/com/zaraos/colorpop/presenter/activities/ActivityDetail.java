package com.zaraos.colorpop.presenter.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zaraos.colorpop.R;
import com.zaraos.colorpop.presenter.fragments.FragmentDetail;
import com.zaraos.colorpop.presenter.utils.BundleInformerUtils;
import com.zaraos.colorpop.presenter.utils.ColorUtils;
import com.zaraos.colorpop.view.utilsview.ToolbarUtilsView;

/**
 * Created by Alex on 31/01/17.
 */

public class ActivityDetail extends AppCompatActivity {

    private ToolbarUtilsView toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_toolbar);
        initToolbar();
        initFragment();
    }



    private void initToolbar() {
        toolbar = new ToolbarUtilsView();
        toolbar.with(this);
    }

    private void initFragment() {
        toolbar.setHomeIndicatorBack("Detalle");
        toolbar.hideElevation();

        ImageView img = (ImageView) findViewById(R.id.toolbar_elevation);

        /*
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, FragmentDetail.newInstance())
                .commit();
*/

        FragmentDetail fragment = FragmentDetail.newInstance();
        boolean isViewBehindStatusBar = false;
        if (android.os.Build.VERSION.SDK_INT >= 19)
            isViewBehindStatusBar = true;

        BundleInformerUtils.init(this)
                .setCircleColor(ColorUtils.get(R.color.blue_grey_800))
                .setPageColor(Color.WHITE)
                .setBaseView(img, BundleInformerUtils.MODE_CENTER, isViewBehindStatusBar)
                .informColorPopPageFragment(fragment);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                .addToBackStack(null)
                .add(android.R.id.content, fragment)
                .commit();
    }

}
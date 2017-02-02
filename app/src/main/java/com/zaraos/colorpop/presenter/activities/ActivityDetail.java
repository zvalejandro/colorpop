package com.zaraos.colorpop.presenter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.zaraos.colorpop.Application;
import com.zaraos.colorpop.R;
import com.zaraos.colorpop.model.PopInformer;
import com.zaraos.colorpop.model.constants.POPAPI;
import com.zaraos.colorpop.presenter.fragments.FragmentDetail;
import com.zaraos.colorpop.presenter.utils.BundlePopUtils;
import com.zaraos.colorpop.presenter.utils.ColorUtils;

/**
 * Created by Alex on 31/01/17.
 */

public class ActivityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.getInstance().getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initFragment();
            }
        }, 400);
    }

    private void initFragment() {

        Fragment fragment = FragmentDetail.newInstance(null);
        PopInformer informer = getIntent().getExtras().getParcelable(POPAPI.POP_INFORMER);

        BundlePopUtils.Builder.init(this)
                .setCircleColor(ColorUtils.get(R.color.white))
                //.setPageColor(Color.WHITE)
                .setBaseView(informer, POPAPI.POP_MODE_CENTER)
                .informColorPopPageFragment(fragment);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                //.addToBackStack(null)
                .replace(android.R.id.content, fragment)
                .commit();
    }

}
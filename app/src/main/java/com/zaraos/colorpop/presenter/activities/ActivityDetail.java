package com.zaraos.colorpop.presenter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

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
        //setContentView(R.layout.main_empty);
        initFragment();
    }

    private void initFragment() {
        Fragment fragment = FragmentDetail.newInstance(null);
        PopInformer dimens = getIntent().getExtras().getParcelable(POPAPI.POP_INFORMER);

        BundlePopUtils.Builder.init(this)
                .setCircleColor(ColorUtils.get(R.color.white))
                .setBaseView(dimens, POPAPI.POP_MODE_CENTER)
                .informFragment(fragment);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                //.replace(R.id.container, fragment)
                .replace(android.R.id.content, fragment)
                .commit();
    }

}
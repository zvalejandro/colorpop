package com.zaraos.colorpop.presenter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.zaraos.colorpop.R;
import com.zaraos.colorpop.model.beans.PopInformer;
import com.zaraos.colorpop.model.constants.MODE;
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
        initFragment();
    }

    private void initFragment() {
        Fragment fragment = FragmentDetail.newInstance(null);
        PopInformer dimens = getIntent().getExtras().getParcelable(POPAPI.BUNDLE_POP_INFORMER);

        BundlePopUtils.Builder.init(this)
                .setCircleColor(ColorUtils.get(R.color.white))
                .setBaseView(dimens, MODE.CENTER)
                .informFragment(fragment);

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(0, R.anim.popup_exit, 0, R.anim.popup_exit)
                .replace(android.R.id.content, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.activity_fade_out);
    }
}